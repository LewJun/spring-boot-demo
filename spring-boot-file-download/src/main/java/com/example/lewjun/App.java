package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * spring boot 启动类
 */
@Slf4j
@SpringBootApplication
@RestController
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(final String pathname) {
//        return ResponseEntity.ok().body(new File("d:/a.txt")); // 错误的使用，将会返回文件名的信息。

        return FileDownloadUtil.getResponseEntity(new File(pathname), true);
    }

}
