package me.feathers.summer.servlet;

import me.feathers.summer.IocContainer;
import me.feathers.summer.anno.*;
import me.feathers.summer.exception.SummerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


public class DispatcherServlet extends HttpServlet {

    private Properties config;

    private List<Class<?>> iocClasses;

    private IocContainer ioc = IocContainer.getIocContainer();

    private Map<String, MappingValueObject> urlMapping = new HashMap<>();

    @Override
    public void init() throws ServletException {

        System.out.println("---------init------");
        // 1. 加载配置文件
        doConfig();
        // 2. 扫描包，获取所有需要注入到ioc的类
        try {
            doScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 3. 对所有扫描到的类进行实例化
        try {
            doInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 4. 将所有uri与控制器方法映射
        doMapping();
    }

    private void doConfig() {
        String location = this.getInitParameter("locations");
        if (location != null && location.length() > 0) {
            try {
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(location);
                config = new Properties();
                config.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doScan() throws Exception {
        String scanPackage = config.getProperty("summer.scanner.package");
        String[] packagePaths = null;
        if (scanPackage == null || scanPackage.trim().length() <= 0) {
            throw new SummerException("请检查您的配置，summer.scanner.package 该配置不可为空");
        }
        packagePaths = scanPackage.split(",");
        List<String> classNames = new ArrayList<>();
        for (String packagePath : packagePaths) {
            String scanPackagePath = packagePath.replaceAll("\\.", "/");
            classNames.addAll(getPackageClassNames(packagePath));
        }
        System.out.println("扫描到的所有类为" + classNames);

        // 加载这些class，并筛选出有指定注解的class
        iocClasses = new LinkedList<>();
        try {
            for (String className : classNames) {
                Class<?> aClass = Class.forName(className);
                if (aClass.getAnnotation(FService.class) != null
                        || aClass.getAnnotation(FController.class) != null) {
                    iocClasses.add(aClass);
                }
            }
        } catch (Exception e) {
            throw new SummerException("加载Class文件出现异常");
        }
        System.out.println("需要注入到IOC的目标类为：" + iocClasses);
    }

    private List<String> getPackageClassNames(String packageName) {
        List<String> classNames = new ArrayList<>();
        String packagePath = packageName.replaceAll("\\.", "/");
        String classAbsolutePath = this.getClass().getClassLoader().getResource(packagePath).getPath();
        File f = new File(classAbsolutePath);
        for (File file : f.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".class") && !file.getName().contains("$")) {
                String className = file.getName().replace(".class", "");
                classNames.add(packageName + "." + className);
            } else if (file.isDirectory()) {
                classNames.addAll(getPackageClassNames(packageName + "." + file.getName()));
            }
        }
        return classNames;
    }

    private void doInstance() throws Exception {
        // 实例化classes，一边实例化，一边注入成员
        // 注入该类 检查autowried 如果autowried的类型已在ioc中，则直接注入，否则重新执行注入类的操作
        for (Class<?> iocClass : iocClasses) {
            autowriedToIoc(iocClass);
        }
        System.out.println("ioc容器中含有的实例现在为：" + ioc);
    }

    // 将一个class类型实例化并注入到ioc容器中
    private void autowriedToIoc(Class<?> clazz) throws Exception {
        if (!ioc.containsObj(clazz)) {
            Object o = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(FAutowired.class) != null) {
                    if (!ioc.containsObj(field.getType())) {
                        autowriedToIoc(field.getType());//注入
                    }
                    field.setAccessible(true);
                    field.set(o, ioc.getByClass(field.getType()));
                }
            }
            ioc.put(toFirstCharLowCase(o.getClass().getSimpleName()), o);
        }
    }

    private void doMapping() {
        // 遍历所有controller
        for (Object o : ioc.getMap().values()) {
            Class<?> aClass = o.getClass();
            if (aClass.getAnnotation(FController.class) != null) {
                FRequestMapping requestMapping = aClass.getAnnotation(FRequestMapping.class);
                if (requestMapping == null) {
                    throw new SummerException("目标控制器" + aClass.getName() + "没有配置FRequestMapping，无法映射");
                }
                String rootPath = requestMapping.value();
                // 遍历拥有FRequestMapping的方法
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method m : declaredMethods) {
                    FRequestMapping methodRequestMapping = m.getAnnotation(FRequestMapping.class);
                    if (methodRequestMapping != null) {
                        String urlPath = methodRequestMapping.value();
                        // 添加到handlerMapping中
                        String url = ("/" + rootPath + "/" + urlPath).replaceAll("[/]+", "/");
                        urlMapping.put(url, new MappingValueObject(m, o));
                    }
                }
            }
        }
        // 初始化handller mapping 结束
        System.out.println("handlerMapping：" + urlMapping);
    }

    private class MappingValueObject {
        private Method method;
        private Object object;
        private LinkedHashMap<String, Object> param;

        public MappingValueObject(Method method, Object o) {
            this.method = method;
            this.object = o;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public Method getMethod() {
            return method;
        }

        public Object getObject() {
            return object;
        }

        public LinkedHashMap<String, Object> getParam() {
            return param;
        }

        public void setParam(LinkedHashMap<String, Object> param) {
            this.param = param;
        }

        @Override
        public String toString() {
            return "MappingValueObject{" +
                    "method=" + method +
                    ", object=" + object +
                    '}';
        }
    }

    private String toFirstCharLowCase(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req, resp);
    }

    private void doHandle(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("request uri: " + req.getRequestURL());
        // 解析uri，匹配控制器
        try {
            URI uri = new URI(req.getRequestURI());
            String path = uri.getPath();
            MappingValueObject mappingValueObject = urlMapping.get(path);
            if (mappingValueObject == null) {
                resp.setStatus(404);
                return;
            }
            Method method = mappingValueObject.getMethod();
            Object object = mappingValueObject.getObject();
            Parameter[] ps = method.getParameters();
            Map parameterMap = req.getParameterMap();
            String[] methodParams = new String[ps.length];
            if (methodParams != null && methodParams.length > 0 && parameterMap == null) {
                resp.setStatus(400);
                return;
            }
            for (int i = 0; i < ps.length; i++) {
                Object o1 = parameterMap.get(ps[i].getAnnotation(FRequestParam.class).value());
                methodParams[i] = ((String[]) o1)[0];
            }
            Object invoke = method.invoke(object, methodParams);
            resp.getWriter().write(String.valueOf(invoke));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
