package com.yangsx95.demo.taskschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 开启Schedule
 * @author yangsx
 */
@EnableScheduling
@SpringBootApplication
public class TaskScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskScheduleApplication.class, args);
    }

}
