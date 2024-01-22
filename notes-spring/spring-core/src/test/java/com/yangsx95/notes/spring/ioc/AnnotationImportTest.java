package com.yangsx95.notes.spring.ioc;

import com.yangsx95.notes.spring.ioc.util.Util;
import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <code>@Import</code> 组件导入注解测试
 */
public class AnnotationImportTest {

    static class Blue {
    }

    static class Red {
    }

    static class RainBow {
    }

    @Configuration
    @Import(Blue.class)
    public static class ImportSingleAnnotationTestConfig {
    }

    /**
     * 导入单个组件，组件类可以不使用@Component定义，这样可以导入第三方jar中的类
     * 组件名称为类的全限定名
     */
    @Test
    public void testImportSingle() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportSingleAnnotationTestConfig.class);
        Util.printApplicationContextBeans(context);
    }

    @Configuration
    @Import({Blue.class, Red.class})
    public static class ImportMultiAnnotationTestConfig {
    }

    /**
     * 导入多个组件
     */
    @Test
    public void testImportMulti() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportMultiAnnotationTestConfig.class);
        Util.printApplicationContextBeans(context);
    }

    static class MyImportSelector implements ImportSelector {
        /**
         * 返回要导入组件的全类名的数组
         *
         * @param importingClassMetadata 标注@Import的类的所有注解信息以及类信息
         *                               这里对应的就是ImportSelectorAnnotationTestConfig类上的注解
         */
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            System.out.println("import 注解所在的类名：" + importingClassMetadata.getClassName());
            System.out.println("import 注解所在的类的所有注解信息：" + importingClassMetadata.getAnnotationTypes());
            // 这里返回要批量注册的bean的类的权限定名
            return new String[]{"com.yangsx95.notes.spring.ioc.ImportAnnotationTest.Blue",
                    "com.yangsx95.notes.spring.ioc.ImportAnnotationTest.Red"};
//            return new String[0]; // 这里不能返回null
        }
    }

    @Configuration
    @Import(MyImportSelector.class)
    static class ImportSelectorAnnotationTestConfig {
    }

    /**
     * 导入选择器
     */
    @Test
    public void testImportSelector() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportSelectorAnnotationTestConfig.class);
        Util.printApplicationContextBeans(context);
    }

    /**
     * 向Bean定义信息注册表中注册BeanDefinition
     */
    static class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
        /**
         * @param importingClassMetadata @Import所在类的注解信息以及类的信息
         * @param registry               BeanDefinition注册表，可以向其中注册BeanDefinition
         */
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            // 如果注册表中有Red，和 Blue，那么就注册一个RainBow（彩虹）
            if (registry.containsBeanDefinition("com.yangsx95.notes.spring.ioc.ImportAnnotationTest$Red")
                    && registry.containsBeanDefinition("com.yangsx95.notes.spring.ioc.ImportAnnotationTest$Blue")) {
                RootBeanDefinition rainBow = new RootBeanDefinition(RainBow.class);
                registry.registerBeanDefinition("red", rainBow);
            }
        }
    }

    @Configuration
    @Import({Red.class, Blue.class, MyImportBeanDefinitionRegistrar.class})
    static class ImportBeanDefinitionRegistrarAnnotationTestConfig {
    }

    @Test
    public void testImportBeanDefinitionRegistrar() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportBeanDefinitionRegistrarAnnotationTestConfig.class);
        Util.printApplicationContextBeans(context);
    }

}
