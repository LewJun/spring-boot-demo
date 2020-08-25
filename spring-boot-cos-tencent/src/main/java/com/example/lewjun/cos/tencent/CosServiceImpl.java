package com.example.lewjun.cos.tencent;

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

    private COSClient initCosClient() {
        final ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        clientConfig.setConnectionRequestTimeout(timeout);
        //请求协议
        clientConfig.setHttpProtocol(HttpProtocol.https);

        return new COSClient(new BasicCOSCredentials(accessKey, secretKey), clientConfig);
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
        final COSClient cosClient = initCosClient();
        try {
            final String key = baseDir + getDirectoryFixed(directory) + newFileName;

            if (metadataMap == null || metadataMap.isEmpty()) {
                cosClient.putObject(new PutObjectRequest(bucketName, key, file));
            } else {
                final ObjectMetadata metadata = new ObjectMetadata();
                metadataMap.forEach(metadata::addUserMetadata);
                cosClient.putObject(new PutObjectRequest(bucketName, key, new FileInputStream(file), metadata));
            }

            return true;
        } catch (final Exception e) {
            log.error("【出现异常：】", e);
            throw new RuntimeException(e);
        } finally {
            shutdown(cosClient);
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
        final COSClient cosClient = initCosClient();
        try {
            return cosClient.getObjectMetadata(new GetObjectMetadataRequest(bucketName, filePath));
        } catch (final Exception e) {
            log.error("【出现异常：】", e);
            throw new RuntimeException(e);
        } finally {
            shutdown(cosClient);
        }
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
        final COSClient cosClient = initCosClient();
        try {
            final File outFile = new File(outputFilePath);
            cosClient.getObject(new GetObjectRequest(bucketName, filePath), outFile);

            return outFile;
        } catch (final Exception e) {
            log.error("【出现异常：】", e);
            throw new RuntimeException(e);
        } finally {
            shutdown(cosClient);
        }
    }

    @Override
    public boolean deleteFile(final String filePath) {
        final COSClient cosClient = initCosClient();
        try {
            cosClient.deleteObject(bucketName, filePath);

            return true;
        } catch (final Exception e) {
            log.error("【出现异常：】", e);
            throw new RuntimeException(e);
        } finally {
            shutdown(cosClient);
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
    public String getFullPath(final String filePath) {
        return domain + baseDir + filePath;
    }

    private void shutdown(final COSClient cosClient) {
        cosClient.shutdown();
    }
}
