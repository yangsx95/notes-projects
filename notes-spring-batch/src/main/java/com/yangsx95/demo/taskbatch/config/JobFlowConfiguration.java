package com.yangsx95.demo.taskbatch.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.Resource;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/7/13
 */
@Profile("flow")
@Configuration
@EnableBatchProcessing
public class JobFlowConfiguration {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 定义一个Job 先执行 Flow:helloFlow ， 如果执行成功，则执行step3()
     */
    @Bean
    public Job helloFlowJob() {
        return jobBuilderFactory.get("helloFlowJob")
                .start(helloFlow())
                .next(step3())
                .end()
                .build();
    }

    @Bean
    public Flow helloFlow() {
        return new FlowBuilder<Flow>("helloFlow")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet((stepContribution, chunkContext) -> {
            System.out.println("==== step 1");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").tasklet((stepContribution, chunkContext) -> {
            System.out.println("==== step 2");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3").tasklet((stepContribution, chunkContext) -> {
            System.out.println("==== step 3");
            return RepeatStatus.FINISHED;
        }).build();
    }
    
}
