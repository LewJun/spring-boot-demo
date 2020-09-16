package com.example.lewjun.utils;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterUtils {
    private PrintWriterUtils() {
    }

    public static void printlnAndFlush(final ServletResponse resp, final Object obj) throws IOException {
        resp.setContentType("application/json;charset=utf-8");

        final PrintWriter writer = resp.getWriter();
        writer.println(JsonUtils.object2String(obj));
        writer.flush();
    }
}
