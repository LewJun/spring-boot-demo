package com.example.lewjun.config;

import com.example.lewjun.job.SayHelloJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    /**
     * 配置SayHelloJob
     */
    public static class SayHelloJobConfig {
        @Value("${spring.quartz.cron.sayHelloJob}")
        private String cronSayHelloJob;

        /**
         * 注入SayHelloJob
         */
        @Bean
        public JobDetail sayHelloJob() {
            return JobBuilder.newJob(SayHelloJob.class)
                    .withIdentity("sayHelloJob")
                    .storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                    .build();
        }

        /**
         * 定义SayHelloJob的触发器
         */
        @Bean
        public Trigger sayHelloJobTrigger() {
            return TriggerBuilder.newTrigger()
                    .withIdentity("sayHelloJobTrigger")
                    .forJob(sayHelloJob()) // 对应job为sayHelloJob
                    .withSchedule(
//                            SimpleScheduleBuilder.simpleSchedule()
//                                    .withIntervalInSeconds(5)
//                                    .repeatForever()
                            CronScheduleBuilder.cronSchedule(cronSayHelloJob)
                    )
                    .build();
        }
    }
}
