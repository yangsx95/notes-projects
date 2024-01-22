package me.feathers.demo.swagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swagger2Application {

    // 启动后访问 http://localhost:8080/swagger-ui.html
    public static void main(String[] args) {
        SpringApplication.run(Swagger2Application.class, args);
    }
}
