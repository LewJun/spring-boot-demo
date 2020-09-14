package com.example.lewjun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
// @EnableScheduling 注解表示开启对@Scheduled注解的解析
@EnableScheduling
// @EnableAsync 注解表示开启@Async注解的解析；作用就是将串行化的任务给并行化了
@EnableAsync
public class SpringTaskConfig {
}
