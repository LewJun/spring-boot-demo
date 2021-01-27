package com.example.lewjun;

import com.example.lewjun.convert.BBiilliiConvert;
import org.junit.Test;

import java.io.File;

public class JunitTest {
    @Test
    public void testConvert() {
        new BBiilliiConvert().convert(new File("/Users/huiye/Desktop/"), new File("/Users/huiye/Desktop/biliconv/"), "ffmpeg");
    }
}
