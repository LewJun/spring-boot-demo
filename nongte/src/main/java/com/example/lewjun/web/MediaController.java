package com.example.lewjun.web;

import com.example.lewjun.util.FileDownloadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private final String uploadPath = "/Users/huiye/Documents/upload/";

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(final String pathname) {
        return FileDownloadUtil.getResponseEntity(new File(pathname), false);
    }

    @PostMapping("/uploadFile")
    public String uploadFile(final MultipartFile file, final String type) {
        log.info("type: {}", type);
        return getPathName(transferTo(file));
    }

    @PostMapping("/uploadFiles")
    public List<String> uploadFiles(final MultipartFile[] files, final String type) {
        log.info("type: {}", type);
        final List<String> filenames = new ArrayList<>();
        for (final MultipartFile file : files) {
            final String originalFilename = file.getOriginalFilename();
            log.info("originalFilename: {}", originalFilename);

            filenames.add(getPathName(transferTo(file)));
        }
        return filenames;
    }

    @PostMapping("/upload")
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
        return path.substring(uploadPath.length());
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

            final File file = new File(dir, prefix + "." + originalFilename);
            multipartFile.transferTo(file);

            return file;

        } catch (final IOException e) {
            log.error("出现异常", e);
        }

        return null;
    }

    /**
     * <img src="file/abc.jpg">
     *
     * @param response
     * @param filename
     * @throws IOException
     */
    @GetMapping("/file/{filename}")
    public void img(final HttpServletResponse response, @PathVariable final String filename) throws IOException {
        final byte[] bytes = FileUtils.readFileToByteArray(new File(uploadPath, filename));
        final ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        IOUtils.closeQuietly(out);
    }

}
