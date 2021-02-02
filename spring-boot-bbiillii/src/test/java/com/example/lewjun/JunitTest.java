package com.example.lewjun;

import com.example.lewjun.convert.BBiilliiConvert;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class JunitTest {
    @Test
    public void testConvert() {
        new BBiilliiConvert().convert(new File("/Users/huiye/Desktop/"), new File("/Users/huiye/Desktop/biliconv/"), "ffmpeg");
    }

    @Test
    public void testFileUtilsWrite() throws IOException {
        FileUtils.writeByteArrayToFile(new File("d:/xxx.txt"), "中文123".getBytes(StandardCharsets.UTF_8));
        FileUtils.writeLines(new File("d:/abc.txt"), "utf-8", Arrays.asList("abc", "ddd", "eee"));
    }
}
