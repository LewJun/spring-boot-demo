package com.example.lewjun.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

// 在类上添加了 Quartz 的 @DisallowConcurrentExecution 注解，保证相同 JobDetail 在多个 JVM 进程中，有且仅有一个节点在执行。
@DisallowConcurrentExecution
@Slf4j
public class SayHelloJob extends QuartzJobBean {
    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        log.info("【sayHelloJob: {}】", context);
    }
}
