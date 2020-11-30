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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        return transferTo(file).getName();
    }

    @PostMapping("/uploadFiles")
    public List<String> uploadFiles(final MultipartFile[] files, final String type) {
        log.info("type: {}", type);
        final List<String> filenames = new ArrayList<>();
        for (final MultipartFile file : files) {
            final String originalFilename = file.getOriginalFilename();
            log.info("originalFilename: {}", originalFilename);

            filenames.add(transferTo(file).getName());
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

            filenames.add(transferTo(multipartFile).getName());
        }
        return filenames;
    }

    private File transferTo(final MultipartFile multipartFile) {
        try {
            final String originalFilename = multipartFile.getOriginalFilename();
            final String prefix = UUID.randomUUID().toString();
            final File tempFile = File.createTempFile(prefix, String.format(".%s", originalFilename));
            final File file = new File(uploadPath, prefix + "." + originalFilename);
            log.info("tempFile: {}", file);
            multipartFile.transferTo(file);

            return tempFile;
        } catch (final IOException e) {
            log.error("出现异常", e);
        }

        return null;
    }

    /**
     * <img src="img/abc.jpg">
     *
     * @param response
     * @param filename
     * @throws IOException
     */
    @GetMapping("/img/{filename}")
    public void showImg(final HttpServletResponse response, @PathVariable final String filename) throws IOException {
        final byte[] bytes = FileUtils.readFileToByteArray(new File(uploadPath, filename));
        final ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        IOUtils.closeQuietly(out);
    }

}
