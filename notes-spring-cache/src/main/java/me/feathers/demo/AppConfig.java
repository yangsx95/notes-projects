package me.feathers.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("me.feathers.demo")
// @ImportResource(locations = {"cache.xml"}) // 使用xml配置方式
// 注解方式不需要任何其他配置，会自动扫描 componentScan 下的所有带有 @Configuration 注解的配置
public class AppConfig {

}
