package com.yangsx95.demo.taskbatch.config;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.Resource;

/**
 * Spring Batch 配置
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/7/13
 */
@Profile("hello")
@Configuration
@EnableBatchProcessing
public class JobHelloConfiguration {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory
                .get("helloWorldJob") // 定义一个名称为helloWorldJob的job
                .start(step1()) // 指定执行的步骤
                .next(step2()) // 多个步骤使用next指定, 这种方式无论上个任务的执行结果是什么都去执行
                    .on(ExitStatus.COMPLETED.toString()).to(step3()) // 如果step2执行后的结果为执行完成，则跳转到step3
                    .on(ExitStatus.FAILED.toString()).fail() 
                .from(step3())
                .end() 
                .build(); // 执行构建，构建Job任务
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() { // 一个Tasklet类型的简单任务，仅包含一个execute方法
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("=== hello spring batch 1");
                        return RepeatStatus.FINISHED; // 返回结束状态，代表此步骤已经执行结束
                    }
                }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").tasklet((stepContribution, chunkContext) -> {
            System.out.println("===  hello spring batch 2");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3").tasklet((stepContribution, chunkContext) -> {
            System.out.println("===  hello spring batch 3");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4").tasklet((stepContribution, chunkContext) -> {
            System.out.println("===  hello spring batch 4");
            return RepeatStatus.FINISHED;
        }).build();
    }
}
