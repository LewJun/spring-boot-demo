package com.example.lewjun.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

@Slf4j
public class FileDownloadUtil {
    public static ResponseEntity<byte[]> getResponseEntity(final File file) {
        return getResponseEntity(file, false);
    }

    public static ResponseEntity<byte[]> getResponseEntity(final File file, final boolean deleteFile) {
        try (final FileInputStream fis = new FileInputStream(file)) {
            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(
                    "Content-Disposition",
                    String.format("attachment;filename=%s", URLEncoder.encode(file.getName(), "utf-8"))
            );
            return ResponseEntity
                    .ok()
                    .headers(httpHeaders)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(IOUtils.toByteArray(fis));
        } catch (final Exception e) {
            log.error("出现异常", e);
        } finally {
            if (deleteFile) {
                // 注意这里把文件删除了。
                file.delete();
            }
        }
        return ResponseEntity
                .badRequest()
                .build();
    }
}
