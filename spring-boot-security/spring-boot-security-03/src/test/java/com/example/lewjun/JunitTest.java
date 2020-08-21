package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

@Slf4j
public class JunitTest {
    @Test
    public void testSomething() {
        new File("d:/").listFiles(new FileFilter() {
            @Override
            public boolean accept(final File pathname) {
                return pathname.getName().endsWith(".txt");
            }
        });
    }
}
