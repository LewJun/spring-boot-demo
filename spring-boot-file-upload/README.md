# spring-boot-file-upload

> 文件上传例子

[TOC]

## 上传单文件 MultipartFile

```java
    @PostMapping("/uploadFile")
    public String uploadFile(final MultipartFile file, String type) {
        log.info("type: {}", type);
        transferTo(file);
        return file.getOriginalFilename();
    }
```

如上，文件上传时，文件的key必须是file，还可以添加一个字段type

## 上传多文件 MultipartFile

```java
    @PostMapping("/uploadFiles")
    public List<String> uploadFiles(final MultipartFile[] files, String type) {
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
```

如上，文件上传时，可以上传多个文件，文件的key必须是files，还可以添加一个字段type

## upload MultipartHttpServletRequest 上传不固定的key字段的文件

如上两种方式，上传的文件都固定了前端的字段key必须是file或者files，除非是和前端就是这样约定的，否则这必然不好。

```java
    @PostMapping("/upload")
    public List<String> uploadFiles(final MultipartHttpServletRequest multipartHttpServletRequest, String type) {
        log.info("type: {}", type);
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        List<String> filenames = new ArrayList<>();
        for (Map.Entry<String, MultipartFile> me : fileMap.entrySet()) {
            String key = me.getKey();
            log.info("key: {}", key);
            MultipartFile multipartFile = me.getValue();

            transferTo(multipartFile);

            filenames.add(multipartFile.getOriginalFilename());
        }
        return filenames;
    }
```

完整[App.java](src/main/java/com/example/lewjun/App.java)

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

