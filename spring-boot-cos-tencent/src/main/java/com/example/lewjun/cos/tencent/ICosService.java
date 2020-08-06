package com.example.lewjun.cos.tencent;

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
