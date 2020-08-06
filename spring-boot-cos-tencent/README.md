# spring-boot-cos-tencent

> 集成腾讯对象存储 COS

[TOC]

## 对象存储 COS 简介 

> 对象存储（Cloud Object Storage，COS）是由腾讯云推出的无目录层次结构、无数据格式限制，可容纳海量数据且支持 HTTP/HTTPS 协议访问的分布式存储服务。腾讯云 COS 的存储桶空间无容量上限，无需分区管理，适用于 CDN 数据分发、数据万象处理或大数据计算与分析的数据湖等多种场景。COS 提供网页端管理界面、多种主流开发语言的 SDK、API 以及命令行和图形化工具，并且兼容 S3 的 API 接口，方便用户直接使用社区工具和插件。 

## 添加依赖
```xml
    <dependencies>
        <!-- 腾讯COS -->
        <dependency>
            <groupId>com.qcloud</groupId>
            <artifactId>cos_api</artifactId>
        </dependency>
    </dependencies>
```

## 配置cos

```yaml
cos:
  accessKey: xxx
  secretKey: yyy
  bucketName: qcjl2019-1300597172
  regionName: ap-chengdu
  baseDir: test/
  timeout: 5000
  domain: https://${cos.bucketName}.cos.${cos.regionName}.myqcloud.com/
```

## 定义接口
[ICosService.java](src/main/java/com/example/lewjun/cos/tencent/ICosService.java)
```java
import com.qcloud.cos.model.ObjectMetadata;

import java.io.File;
import java.util.Map;

public interface ICosService {

    /**
     * 假设根路径为/root，上传文件1.txt，位置为/root/1.txt
     *
     * @param file 文件
     * @return 成功则返回true，否则返回false
     */
    boolean uploadFile(File file);

    /**
     * 假设根路径为/root，上传文件1.txt，目录为dir，位置为/root/dir/1.txt
     *
     * @param directory 目录
     * @param file      文件
     * @return 成功则返回true，否则返回false
     */
    boolean uploadFile(String directory, File file);

    /**
     * 上传文件
     *
     * @param directory
     * @param file
     * @param metadataMap
     * @return
     */
    boolean uploadFile(String directory, File file, Map<String, String> metadataMap);

    /**
     * 假设根路径为/root，上传文件1.txt，位置为/root/1.txt
     *
     * @param directory
     * @param newFileName
     * @param file
     * @param metadataMap
     * @return 成功则返回true，否则返回false
     */
    boolean uploadFile(String directory, String newFileName, File file, Map<String, String> metadataMap);

    /**
     * 获取对象元数据map
     * @param filePath
     * @return
     */
    Map<String, Object> getObjectMetadataMap(String filePath);

    /**
     * 获取对象元数据
     * @param filePath
     * @return
     */
    ObjectMetadata getObjectMetadata(final String filePath);

    /**
     * 下载文件
     *
     * @param filePath 文件路径，从根路径算
     * @return 成功则返回文件
     */
    File downloadFile(String filePath, String outputFilePath);

    /**
     * 删除文件
     *
     * @param filePath 文件路径，从根路径算
     * @return 成功则返回true，否则返回false
     */
    boolean deleteFile(String filePath);

    /**
     * 获取文件上传路径
     * @param fileName
     * @return
     */
    String getFilePath(final String fileName);

    /**
     * 获取文件上传路径
     *
     * @return 成功则返回true，否则返回false
     */
    String getFilePath(String directory, String fileName);

    String getFullPath(String filePath);
}
```

## 实现接口

[CosServiceImpl.java](src/main/java/com/example/lewjun/cos/tencent/CosServiceImpl.java)

```java
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GetObjectMetadataRequest;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CosServiceImpl implements ICosService {
    @Value("${cos.bucketName}")
    private String bucketName;
    @Value("${cos.accessKey}")
    private String accessKey;
    @Value("${cos.secretKey}")
    private String secretKey;
    @Value("${cos.regionName}")
    private String regionName;
    @Value("${cos.baseDir}")
    private String baseDir;
    @Value("${cos.domain}")
    private String domain;
    @Value("${cos.timeout}")
    private int timeout;

    private COSClient cosClient;

    @PostConstruct
    private void initCosClient() {
        final ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        clientConfig.setConnectionRequestTimeout(timeout);
        //请求协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        this.cosClient = new COSClient(new BasicCOSCredentials(accessKey, secretKey), clientConfig);
    }

    @Override
    public boolean uploadFile(final File file) {
        return uploadFile("", file);
    }

    @Override
    public boolean uploadFile(final String directory, final File file) {
        return uploadFile(directory, file, null);
    }

    @Override
    public boolean uploadFile(final String directory, final File file, final Map<String, String> metadataMap) {
        return uploadFile(directory, file.getName(), file, metadataMap);
    }

    @Override
    public boolean uploadFile(final String directory, final String newFileName, final File file,
                              final Map<String, String> metadataMap) {
        try {
            final String key = baseDir + getDirectoryFixed(directory) + newFileName;

            if (metadataMap == null || metadataMap.isEmpty()) {
                cosClient.putObject(new PutObjectRequest(bucketName, key, file));
            } else {
                final ObjectMetadata metadata = new ObjectMetadata();
                metadataMap.forEach(metadata::addUserMetadata);
                cosClient.putObject(new PutObjectRequest(bucketName, key, new FileInputStream(file), metadata));
            }

            cosClient.shutdown();

            return true;
        } catch (final Exception e) {
            log.error("【出现异常：】", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> getObjectMetadataMap(final String filePath) {
        final ObjectMetadata objectMetadata = getObjectMetadata(filePath);

        final Map<String, Object> ret = new HashMap<>();
        ret.putAll(objectMetadata.getRawMetadata());
        ret.putAll(objectMetadata.getUserMetadata());

        return ret;
    }


    @Override
    public ObjectMetadata getObjectMetadata(final String filePath) {
        return cosClient.getObjectMetadata(new GetObjectMetadataRequest(bucketName, filePath));
    }

    private String getDirectoryFixed(String directory) {
        if (directory == null) return "";
        if (!directory.equals("") && !directory.endsWith("/")) {
            directory += "/";
        }
        return directory;
    }

    @Override
    public File downloadFile(final String filePath, final String outputFilePath) {
        try {
            final File outFile = new File(outputFilePath);
            cosClient.getObject(new GetObjectRequest(bucketName, filePath), outFile);
            cosClient.shutdown();

            return outFile;
        } catch (final Exception e) {
            log.error("【出现异常：】", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteFile(final String filePath) {
        try {
            cosClient.deleteObject(bucketName, filePath);
            cosClient.shutdown();

            return true;
        } catch (final Exception e) {
            log.error("【出现异常：】", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFilePath(final String directory, final String fileName) {
        return baseDir + getDirectoryFixed(directory) + fileName;
    }

    public String getFilePath(final String fileName) {
        return getFilePath("", fileName);
    }

    @Override
    public String getFullPath(String filePath) {
        return domain + filePath;
    }

}
```

## 注入ICosService

[CosConfig.java](src/main/java/com/example/lewjun/config/CosConfig.java)

```java
@Component
public class CosConfig {
    @Bean
    public ICosService cosService() {
        return new CosServiceImpl();
    }
}
```

## 使用

[AppTest.java](src/test/java/com/example/lewjun/AppTest.java)

```java
/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class AppTest {
    @Autowired
    private ICosService cosService;

    @Test
    public void testLoadContext() throws FileNotFoundException {
// 指定目录
        final String directory = "dir/";

// 本地要上传的文件
        final File file = new File("I:/1.txt");
//        上传文件到根目录
//        cosService.uploadFile(file);

//        上传文件到根目录下的指定目录
//        cosService.uploadFile(directory, file);

        final Map<String, String> metadata = new HashMap<>();
        metadata.put("file-type-x", "text/plain");
//        上传文件到根目录下的指定目录，并且制定为一个新名称，添加元数据
        cosService.uploadFile(directory, "new_file_name.txt", file, metadata);

//        获取文件路径
        final String filePath = cosService.getFilePath(directory, "new_file_name.txt");
        log.info("【filePath: {}】", filePath);

//        获取文件全路径
        log.info("【fullPath: {}】", cosService.getFullPath(filePath));

//        根据文件路径获取元数据
        log.info("【metaInfo: {}】", cosService.getObjectMetadataMap(filePath));

//        下载文件到本地指定目录
        cosService.downloadFile(filePath, "d:/a/1.txt");
        //        文件删除
        cosService.deleteFile(filePath);
    }
}
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

