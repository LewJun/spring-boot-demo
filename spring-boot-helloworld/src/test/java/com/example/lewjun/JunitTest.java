package com.example.lewjun;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JunitTest {
    @Test
    public void test1() {
        final List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(4);
        integers.add(1);

        integers.remove(Collections.max(integers));
        integers.remove(Collections.min(integers));
        System.out.println(integers);
    }
}
