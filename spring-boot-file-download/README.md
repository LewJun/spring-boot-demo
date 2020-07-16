# spring-boot-file-download

> 文件下载例子

[TOC]

## [FileDownloadUtil.java](src/main/java/com/example/lewjun/FileDownloadUtil.java)

```java
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
    public static ResponseEntity<byte[]> getResponseEntity(File file) {
        return getResponseEntity(file, false);
    }

    public static ResponseEntity<byte[]> getResponseEntity(File file, boolean deleteFile) {
        try (final FileInputStream fis = new FileInputStream(file)) {
            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition",
                    String.format("attachment;filename=%s", URLEncoder.encode(file.getName(), "utf-8")));
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
```

## [App.java](src/main/java/com/example/lewjun/App.java)

```java
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
//        return ResponseEntity.ok().body(new File("d:/a.txt")); // 这样用是错误的，将会返回文件名的信息。

        return FileDownloadUtil.getResponseEntity(new File(pathname), true);
    }

}
```

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo
```

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

