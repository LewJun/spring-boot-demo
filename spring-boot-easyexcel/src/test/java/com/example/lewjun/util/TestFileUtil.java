package com.example.lewjun.util;

import java.io.File;
import java.io.InputStream;

public class TestFileUtil {
    public static InputStream getResourcesFileInputStream(final String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return TestFileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(final String pathName) {
        final File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(final String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(final String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }
}