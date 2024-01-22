package com.yangsx95.demo.taskschedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 一个用于打印的定时任务
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/7/7
 */
@Component
public class PrintTask {

    /**
     * 指定cron表达式执行
     */
    //@Scheduled(cron = "0 10 * * * *")
    public void cron() throws Exception {
        System.out.println("cron：" + new Date());
    }


    /**
     * 固定频率执行。间隔1s执行一次，如果上次执行未结束，仍然触发执行，会有重复执行的问题
     */
    //@Scheduled(fixedRate = 1000)
    public void fixedRate() throws Exception {
        Thread.sleep(2000);
        System.out.println("fixedRate：" + new Date());
    }

    /**
     * 固定延迟执行。第一次执行完毕后，间歇1s钟，再次执行下一次定时任务，与fixedRate相反
     */
    //@Scheduled(fixedDelay = 1000)
    public void fixedDelay() throws Exception {
        Thread.sleep(3000);
        System.out.println("fixedDelay：" + new Date(System.currentTimeMillis()));
    }

    /**
     * 初始延迟执行。用于设置第一次执行的延迟时间，比如如下则是延迟10s执行定时任务，每次任务执行后，间隔2s再执行下一次
     */
    @Scheduled(initialDelay = 1000 * 10, fixedDelay = 1000 * 2)
    public void initialDelay() throws Exception {
        System.out.println("initialDelay + fixedDelay：" + new Date(System.currentTimeMillis()));
    }

}
