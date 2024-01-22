package com.yangsx95.demo.taskbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author yangsx
 */
@SpringBootApplication
public class TaskScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskScheduleApplication.class, args);
    }

}
