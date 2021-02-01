package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @PostMapping("/uploadFile")
    public String uploadFile(final MultipartFile file, final String type) {
        log.info("type: {}", type);
        transferTo(file);
        return file.getOriginalFilename();
    }

    @PostMapping("/uploadFiles")
    public List<String> uploadFiles(final MultipartFile[] files, final String type) {
        log.info("type: {}", type);
        final List<String> filenames = new ArrayList<>();
        for (final MultipartFile file : files) {
            final String originalFilename = file.getOriginalFilename();
            log.info("originalFilename: {}", originalFilename);
            filenames.add(originalFilename);

            transferTo(file);

        }
        return filenames;
    }

    @PostMapping("/upload")
    public List<String> uploadFiles(final MultipartHttpServletRequest multipartHttpServletRequest, final String type) {
        log.info("type: {}", type);
        final Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        final List<String> filenames = new ArrayList<>();
        for (final Map.Entry<String, MultipartFile> me : fileMap.entrySet()) {
            final String key = me.getKey();
            log.info("key: {}", key);
            final MultipartFile multipartFile = me.getValue();

            transferTo(multipartFile);

            filenames.add(multipartFile.getOriginalFilename());
        }
        return filenames;
    }

    private File transferTo(final MultipartFile multipartFile) {
        try {
            final String originalFilename = multipartFile.getOriginalFilename();
            final String prefix = UUID.randomUUID().toString();
            final File tempFile = File.createTempFile(prefix, String.format(".%s", originalFilename));
            log.info("tempFile: {}", tempFile);
            // 会出现FileExistException
//            multipartFile.transferTo(tempFile);

            final FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(multipartFile.getBytes());
            fos.close();
            return tempFile;
        } catch (final IOException e) {
            log.error("出现异常", e);
        }

        return null;
    }

    @GetMapping("/")
    public String index() {
        return "/";
    }
}
