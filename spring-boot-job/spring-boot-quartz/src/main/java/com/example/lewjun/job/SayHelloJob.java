package com.example.lewjun.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class SayHelloJob extends QuartzJobBean {
    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        log.info("【sayHelloJob: {}】", context);
    }
}
