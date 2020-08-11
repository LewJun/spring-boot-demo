# Stream的使用

[TOC]

```java
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamTest {
    @Test
    public void test1() {
        final List<String> words = Arrays.asList("hello", "world", null, "", "China");
        words.stream().filter(string -> string != null && !string.isEmpty())
                .filter(string -> string.contains("h"))
                .map(String::toUpperCase)
//                .map(String::length)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        final IntSummaryStatistics ints10 = new Random().ints().limit(10).sorted().summaryStatistics();
        log.info("【sum: {}】", ints10.getSum());
        log.info("【avg: {}】", ints10.getAverage());
        log.info("【max: {}】", ints10.getMax());
        log.info("【min: {}】", ints10.getMin());
        log.info("【count: {}】", ints10.getCount());
    }

    // flatMap合并为一个stream
    @Test
    public void testFlatMap() {
        final List<String> words = Arrays.asList("hello", "world");
        words.stream()
                // Stream<String[]>
//                .map(string -> string.split(""))
                // Stream<Stream>
//                .flatMap(Arrays::stream)
                .flatMap(new Function<String, Stream<String>>() {
                    @Override
                    public Stream<String> apply(final String s) {
                        return Arrays.stream(s.split(""));
                    }
                })
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("------------------");

        final List<List<String>> oldList = Arrays.asList(
                Arrays.asList("a", "1"),
                Arrays.asList("b", "c"),
                Arrays.asList("d", "e", "f")
        );

        oldList.stream()
                .flatMap(strings -> strings.stream())
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }
}
```
