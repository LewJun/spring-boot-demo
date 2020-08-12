package com.example.lewjun.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis 配置
 */
@Configuration
@MapperScan(basePackages = {"com.example.lewjun.mapper"})
@EnableTransactionManagement
public class MybatisConfig {
}
