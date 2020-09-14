package com.example.lewjun.scheduleds;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringScheduled {

    @Async
    @Scheduled(cron = "0/1 * * * * *")
    public void scheduledPerSeconds() throws InterruptedException {
        Thread.sleep(3000);
        log.info("【scheduledPerSeconds @Async 每一秒执行一次: {}】", System.currentTimeMillis());
    }

    @Scheduled(cron = "0/1 * * * * *")
    public void scheduledPerSeconds3() throws InterruptedException {
        Thread.sleep(3000);
        log.info("【scheduledPerSeconds3 上次执行完毕后，隔3秒继续执行: {}】", System.currentTimeMillis());
    }
}
