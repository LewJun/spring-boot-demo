package com.example.lewjun.web;

import com.example.lewjun.util.FileDownloadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RestController
public class MediaController {
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(final String pathname) {
        return FileDownloadUtil.getResponseEntity(new File("d:/a.jpg"), false);
    }
}
