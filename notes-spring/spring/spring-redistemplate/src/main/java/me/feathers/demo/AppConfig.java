package me.feathers.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("me.feathers.demo")
@ImportResource(locations = {"spring/redis.xml"})
public class AppConfig {
}
