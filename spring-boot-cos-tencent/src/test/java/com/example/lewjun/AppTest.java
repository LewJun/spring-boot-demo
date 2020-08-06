package com.example.lewjun;

import com.example.lewjun.cos.tencent.ICosService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

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
