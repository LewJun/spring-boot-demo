package com.example.lewjun;

import com.example.lewjun.model.ApplicationProperties;
import com.example.lewjun.model.DatasourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring boot 启动类
 */
@SpringBootApplication
@RestController
@Slf4j
public class App {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private DatasourceProperties datasourceProperties;

    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    public String index() {
        log.info("datasourceProperties: {}", datasourceProperties);
        log.info("applicationProperties: {}", applicationProperties);

        return applicationProperties.toString();
    }
}
