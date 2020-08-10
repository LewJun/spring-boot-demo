package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Slf4j
public class FunctionalInterfaceTest {

    @Test
    public void testListFiles() {
        final File file = new File("d:/");
        final List<File> files = Arrays.asList(file.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(final File pathname) {
                        return pathname.getName().endsWith(".txt");
                    }
                })
        );

        log.info("【files: {}】", files);
    }


    @Test
    public void testPredicate() {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null, -100, -200, 300);

        integers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(final Integer integer) {
                log.info("【foreach: {}】", integer);
            }
        });


        integers.forEach(integer -> log.info("【foreach: {}】", integer));


        eval(integers, integer -> integer != null && integer % 2 == 0);
        eval(integers, integer -> integer != null && integer % 2 == 1);

        // 利于阅读
        eval(3, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer == 3;
            }
        });

        // 还好
        eval(3, integer -> {
            // 返回boolean
            return integer == 3;
        });

        // 不利于阅读
        eval(3, integer -> integer == 3);

    }

    private void eval(final List<Integer> integers, final Predicate<Integer> predicate) {
        final List<Integer> integerList = new ArrayList<>();
        integers.forEach(integer -> {
            if (predicate.test(integer)) {
                integerList.add(integer);
            }
        });
        System.out.println("符合要求 " + integerList);
    }

    private void eval(final Integer integer, final Predicate<Integer> predicate) {
        if (predicate.test(integer)) {
            System.out.println("符合要求");
        } else {
            System.out.println("不符合要求");
        }
    }

    @Test
    public void testFunctionalInterface() {
        new FunctionalInterface1() {

            @Override
            public void print(final String msg) {
                System.out.println("print " + msg);
            }
        }.print("some message");

        ((FunctionalInterface1) msg -> System.out.println("print " + msg)).print("some message");

        final FunctionalInterface1 functionalInterface1 = (msg -> System.out.println("print " + msg));
        functionalInterface1.print("some message");
    }

    @FunctionalInterface
            // 限定该类最多只有一个抽象方法，出现两个就会报告编译异常
    interface FunctionalInterface1 {
        void print(String msg);

        // err
        // void ping();
    }
}
