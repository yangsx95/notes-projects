package me.feathers.summer;

import me.feathers.summer.exception.SummerException;

import java.util.HashMap;
import java.util.Map;

/**
 * IOC容器
 */
public class IocContainer {

    private static IocContainer iocContainer;

    private Map<String, Object> map;

    private IocContainer() {
        map = new HashMap<>();
    }

    public static IocContainer getIocContainer() {
        if (iocContainer == null) {
            iocContainer = new IocContainer();
        }
        return iocContainer;
    }

    public void put(String key, Object obj) {
        if (map.containsKey(key)) {
            throw new SummerException("IOC容器中已包含key为" + key + "的元素");
        }
        map.put(key, obj);
    }

    public Object get(String key) {
        if (!map.containsKey(key)) {
            return null;
        } else {
            return map.get(key);
        }
    }

    public Object getByClassName(String classFullName) {
        try {
            Class<?> aClass = Class.forName(classFullName);
            String simpleName = aClass.getSimpleName();
            return get(toLowerCaseFirstOne(simpleName));
        } catch (Exception e) {
            return null;
        }
    }

    public Object getByClass(Class<?> clazz) {
        return getByClassName(clazz.getName());
    }

    public boolean containsObj(String key) {
        return map.containsKey(key);
    }

    public boolean containsObj(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        return containsObj(toLowerCaseFirstOne(simpleName));
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    @Override
    public String toString() {
        return "ioc:" + map;
    }
}
