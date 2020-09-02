package com.example.lewjun;

import com.example.lewjun.convert.BBiilliiConvert;
import org.junit.Test;

import java.io.File;

public class JunitTest {
    @Test
    public void testConvert() {
        new BBiilliiConvert().convert(new File("F:\\BILI\\tv.danmaku.bili\\download"), new File("f:\\biliconv"), "D:/Program Files/ffmpeg/ffmpeg.exe");
    }
}
