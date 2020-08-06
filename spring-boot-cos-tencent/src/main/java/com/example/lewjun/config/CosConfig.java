package com.example.lewjun.config;

import com.example.lewjun.cos.tencent.CosServiceImpl;
import com.example.lewjun.cos.tencent.ICosService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CosConfig {
    @Bean
    public ICosService cosService() {
        return new CosServiceImpl();
    }
}
