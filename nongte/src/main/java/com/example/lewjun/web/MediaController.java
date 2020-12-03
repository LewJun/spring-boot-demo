package com.example.lewjun.web;

import com.example.lewjun.util.FileDownloadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
public class MediaController {

    @Value("${uploadPath}")
    private String uploadPath;

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(final String pathname) {
        return FileDownloadUtil.getResponseEntity(new File(pathname), false);
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(final MultipartFile file, final String type) {
        log.info("type: {}", type);
        return getPathName(transferTo(file));
    }

    @PostMapping("/wangEditor/uploadFile")
    @ResponseBody
    public String wangEditorUploadFile(@RequestParam("file") final MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "{\"errno\": -1, \"msg\": \"文件不存在\"}";
        }
        return "{\"errno\": 0, \"data\":[\"file?filename="+getPathName(transferTo(file))+"\"]}";
    }

    @PostMapping("/uploadFiles")
    @ResponseBody
    public List<String> uploadFiles(final MultipartFile[] files, final String type) {
        log.info("type: {}", type);
        final List<String> filenames = new ArrayList<>();
        for (final MultipartFile file : files) {
            filenames.add(getPathName(transferTo(file)));
        }
        return filenames;
    }

    @PostMapping("/upload")
    @ResponseBody
    public List<String> uploadFiles(final MultipartHttpServletRequest multipartHttpServletRequest, final String type) {
        log.info("type: {}", type);
        final Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        final List<String> filenames = new ArrayList<>();
        for (final Map.Entry<String, MultipartFile> me : fileMap.entrySet()) {
            final String key = me.getKey();
            log.info("key: {}", key);
            final MultipartFile multipartFile = me.getValue();

            transferTo(multipartFile);

            filenames.add(getPathName(transferTo(multipartFile)));
        }
        return filenames;
    }

    private String getPathName(final File file) {
        final String path = file.getPath();
        return path.substring(uploadPath.length()).replaceAll("\\\\", "/");
    }

    private File transferTo(final MultipartFile multipartFile) {
        try {
            final Calendar cal = Calendar.getInstance();
            final int year = cal.get(Calendar.YEAR);
            final int month = cal.get(Calendar.MONTH);

            final String originalFilename = multipartFile.getOriginalFilename();
            final String prefix = UUID.randomUUID().toString();

            final File dir = new File(uploadPath, String.format("/%s/%s/", year, month));
            if (!dir.exists()) {
                dir.mkdirs();
            }

//            final File file = new File(dir, prefix + "." + originalFilename);
            final File file = new File(dir, prefix + "." + FilenameUtils.getExtension(originalFilename));
            multipartFile.transferTo(file);

            return file;

        } catch (final IOException e) {
            log.error("出现异常", e);
        }

        return null;
    }

    /**
     * <img src="file?filename=xxx.jpg">
     *
     * @param response
     * @param filename
     * @throws IOException
     */
    @GetMapping("/file")
    public void file(final HttpServletResponse response, @RequestParam final String filename) throws IOException {
        final byte[] bytes = FileUtils.readFileToByteArray(new File(uploadPath, filename));
        final ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        IOUtils.closeQuietly(out);
    }

}
