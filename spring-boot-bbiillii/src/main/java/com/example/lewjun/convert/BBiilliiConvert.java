package com.example.lewjun.convert;

import com.example.lewjun.model.Entry;
import com.example.lewjun.model.Ep;
import com.example.lewjun.model.Page_data;
import com.example.lewjun.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public class BBiilliiConvert {
    private static final String doubleQuota = "\"";

    private File shFile;

    public void convert(final File bilibiliDownloadDir, final File outPutDir, final String ffmpegPath) {
        shFile = new File(outPutDir, System.currentTimeMillis() + "bili.sh");
        if (bilibiliDownloadDir == null || bilibiliDownloadDir.isFile()) {
            throw new IllegalArgumentException("需要选择正确的下载目录");
        }

        final String separator = File.separator;

        Arrays.stream(Objects.requireNonNull(bilibiliDownloadDir.listFiles())).forEach(new Consumer<File>() {
            @Override
            public void accept(final File dir1) {
                log.info("【dir1 name: {}】", dir1.getName());
                if (dir1.isDirectory()) {
                    Arrays.stream(Objects.requireNonNull(dir1.listFiles())).forEach(new Consumer<File>() {
                        @Override
                        public void accept(final File subDir) {
                            log.info("【subDir name: {}】", subDir.getName());

                            final String subDirPath = subDir.getPath();
                            log.info("【subDirPath: {}】", subDirPath);

                            Arrays.stream(Objects.requireNonNull(subDir.listFiles(new FilenameFilter() {
                                @Override
                                public boolean accept(final File dir, final String name) {
                                    return "entry.json".equals(name);
                                }
                            }))).forEach(new Consumer<File>() {
                                @Override
                                public void accept(final File jsonFile) {
                                    log.info("【jsonFile: {}】", jsonFile.getName());

                                    try {
                                        final Entry entry = GsonUtil.jsonStringToObj(FileUtils.readFileToString(jsonFile, StandardCharsets.UTF_8.name()), Entry.class);
                                        log.info("【entry: {}】", entry);

                                        final File biliconv = new File(outPutDir, entry.getTitle());
                                        if (!biliconv.exists()) {
                                            biliconv.mkdirs();
                                        }
                                        final String videoPath = subDirPath + separator + entry.getType_tag();

                                        log.info("【videoPath: {}】", videoPath);

                                        final File videoDir = new File(videoPath);

                                        handlerBlv(entry, biliconv, videoPath, videoDir, ffmpegPath, separator);

                                        final File[] m4ses = videoDir.listFiles(new FilenameFilter() {
                                            @Override
                                            public boolean accept(final File dir, final String name) {
                                                return name != null && (name.equals("video.m4s") || name.equals("audio.m4s"));
                                            }
                                        });

                                        if (m4ses != null && m4ses.length > 0) {

                                            handlerPage_data(entry, biliconv, videoPath, ffmpegPath, separator);

                                            handlerEp(entry, biliconv, videoPath, ffmpegPath, separator);

                                        }
                                    } catch (final IOException e) {
                                        log.error("【出现异常：】", e);
                                    }
                                }
                            });
                        }
                    });
                }

            }
        });
    }

    private void handlerBlv(final Entry entry, final File biliconv, final String videoPath, final File videoDir, final String ffmpegPath, final String separator) throws IOException {
        final File[] files = videoDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String name) {
                return name != null && name.endsWith(".blv");
            }
        });

        if (files == null || files.length == 0) return;

        final List<File> blvs = Arrays.stream(files).sorted(new Comparator<File>() {
            @Override
            public int compare(final File o1, final File o2) {
                return Integer.parseInt(FilenameUtils.getBaseName(o1.getName())) - Integer.parseInt(FilenameUtils.getBaseName(o2.getName()));
            }
        }).collect(Collectors.toList());

        // 如果文件blv.txt存在，则删除
        final File blvTxt = new File(videoPath, "blv.txt");
        if (blvTxt.exists()) {
            blvTxt.delete();
        }

        if (!CollectionUtils.isEmpty(blvs)) {
            log.info("【blvs: {}】", blvs);
            for (final File blv : blvs) {
                FileUtils.write(blvTxt, "file '" + blv.getPath() + "'\n", "utf-8", true);
                log.info("【blvTxt: {}】", blvTxt.getPath());
            }

            String fileName = "";
            final Page_data page_data = entry.getPage_data();
            if (page_data != null) {
                fileName = page_data.getPage()
                        + page_data.getPart();
            } else if (entry.getEp() != null) {
                final Ep ep = entry.getEp();
                fileName = ep.getIndex()
                        + " "
                        + ep.getIndex_title();
            } else {

            }

            final String cmd = doubleQuota
                    + ffmpegPath
                    + doubleQuota
                    + " -f concat -safe 0 -i "
                    + doubleQuota
                    + videoPath
                    + separator
                    + "blv.txt"
                    + doubleQuota
                    + " -c copy "
                    + doubleQuota
                    + biliconv.getPath()
                    + separator
                    + fileName
                    + ".mp4"
                    + doubleQuota;

            log.error("【cmd: {}】", cmd);
            FileUtils.write(shFile, cmd + "\n", "utf-8", true);
        }
    }

    private void handlerPage_data(final Entry entry, final File biliconv, final String videoPath, final String ffmpegPath, final String separator) throws IOException {
        final Page_data page_data = entry.getPage_data();
        if (page_data != null) {
            final String cmd = doubleQuota
                    + ffmpegPath
                    + doubleQuota
                    + " -i "
                    + doubleQuota
                    + videoPath
                    + separator
                    + "video.m4s"
                    + doubleQuota
                    + " -i "
                    + doubleQuota
                    + videoPath
                    + separator
                    + "audio.m4s"
                    + doubleQuota
                    + " -codec copy "
                    + doubleQuota
                    + biliconv.getPath()
                    + separator
                    + page_data.getPart()
                    + ".mp4"
                    + doubleQuota;

            log.error("【page data cmd: {}】", cmd);
            FileUtils.write(shFile, cmd + "\n", "utf-8", true);
        }
    }

    private void handlerEp(final Entry entry, final File biliconv, final String videoPath, final String ffmpegPath, final String separator) throws IOException {
        final Ep ep = entry.getEp();
        if (ep != null) {
            final String cmd = doubleQuota
                    + ffmpegPath
                    + doubleQuota
                    + " -i "
                    + doubleQuota
                    + videoPath
                    + separator
                    + "video.m4s"
                    + doubleQuota
                    + " -i "
                    + doubleQuota
                    + videoPath
                    + separator
                    + "audio.m4s"
                    + doubleQuota
                    + " -codec copy "
                    + doubleQuota
                    + biliconv.getPath()
                    + separator
                    + ep.getIndex()
                    + " "
                    + ep.getIndex_title()
                    + ".mp4"
                    + doubleQuota;

            log.error("【ep cmd: {}】", cmd);
            FileUtils.write(shFile, cmd + "\n", "utf-8", true);
        }
    }
}
