# Java8 Base64的使用

在Java 8中，Base64编码已经成为Java类库的标准。

Java 8 内置了 Base64 编码的编码器和解码器。

Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：

* 基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
*    URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
*    MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。


[Base64Test.java](src/test/java/com/example/lewjun/Base64Test.java)
```java

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Slf4j
public class Base64Test {
    @Test
    public void testEncode() {
        final String encodeToString = Base64.getEncoder().encodeToString("/ab01?aab001=10&aab002=中国".getBytes(StandardCharsets.UTF_8));
        log.info("【encodeToString: {}】", encodeToString);// 【encodeToString: L2FiMDE/YWFiMDAxPTEwJmFhYjAwMj3kuK3lm70=】

        final String decodeToString = new String(Base64.getDecoder().decode(encodeToString), StandardCharsets.UTF_8);
        log.info("【decodeToString: {}】", decodeToString);// 【decodeToString: /ab01?aab001=10&aab002=中国】
    }

    @Test
    public void testUrlEncode() {
        final String encodeToString = Base64.getUrlEncoder().encodeToString("/ab01?aab001=10&aab002=中国".getBytes(StandardCharsets.UTF_8));
        log.info("【encodeToString: {}】", encodeToString);// 【encodeToString: L2FiMDE/YWFiMDAxPTEwJmFhYjAwMj3kuK3lm70=】

        final String decodeToString = new String(Base64.getUrlDecoder().decode(encodeToString), StandardCharsets.UTF_8);
        log.info("【decodeToString: {}】", decodeToString);// 【decodeToString: /ab01?aab001=10&aab002=中国】
    }

    @Test
    public void testMimeEncode() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; ++i) {
            stringBuilder.append(UUID.randomUUID().toString());
        }

        // 编码使用 MIME 型 base64 编码方案
        final String encodeToString = Base64.getMimeEncoder().encodeToString(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        log.info("【encodeToString: {}】", encodeToString);

        final String decodeToString = new String(Base64.getMimeDecoder().decode(encodeToString), StandardCharsets.UTF_8);
        log.info("【decodeToString: {}】", decodeToString);

//        17:49:52.033 [main] INFO com.example.lewjun.Base64Test - 【encodeToString: MGQyYjI3MDEtYzEwMS00Y2U3LTlmZDItNTNjYmJmZTdhYzljYmYwY2RhMTYtY2YzYS00YTIyLTg5
//                ZmEtZGY1ODU0NDEzZDk3MjUwYWVjYzgtNWY4Zi00ODEyLTlhMmYtOTc0YmRjYjJkOTRhMzNhZjZh
//        YTAtYTVkZC00YTkwLTg0OGUtY2NmOWM1NWRlNzI3MDlmNmIzNjctMzFlZC00NDZmLWFkMDMtOTk0
//                ZDI0YWIyODcyNDkxOGI4ZmUtMGM4ZC00MmVhLWFlZjMtMzA2MmU1NzVmZmNjYjI3M2UzODctNDk1
//        NC00ZDFjLWFlYTYtYjdlNTMwMTNlZjc4Mzg0ODA1ZDMtZDQ4NS00NmRiLTg3NDUtMmRhNDhlMGFj
//                N2ViN2I4MTZhYWEtZmU1Ni00NTQwLWI3NmQtNmQyOTA4NWQxNGRlYzMzMmZiMzQtNjg5Ni00ZDFi
//        LTk1YzUtZmZiNzBkOTQ4MmY1】
//        17:49:52.042 [main] INFO com.example.lewjun.Base64Test - 【decodeToString: 0d2b2701-c101-4ce7-9fd2-53cbbfe7ac9cbf0cda16-cf3a-4a22-89fa-df5854413d97250aecc8-5f8f-4812-9a2f-974bdcb2d94a33af6aa0-a5dd-4a90-848e-ccf9c55de72709f6b367-31ed-446f-ad03-994d24ab28724918b8fe-0c8d-42ea-aef3-3062e575ffccb273e387-4954-4d1c-aea6-b7e53013ef78384805d3-d485-46db-8745-2da48e0ac7eb7b816aaa-fe56-4540-b76d-6d29085d14dec332fb34-6896-4d1b-95c5-ffb70d9482f5】
    }
}
```