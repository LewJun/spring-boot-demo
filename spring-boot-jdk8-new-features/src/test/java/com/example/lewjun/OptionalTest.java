package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

@Slf4j
public class OptionalTest {
    @Test
    public void testSum() {
        final Integer x = null;
        final Integer y = 4;

        log.info("【sum: {}】", sum(x, y));
    }

    private Integer sum(final Integer x, final Integer y) {
        if (x == null) throw new IllegalArgumentException("illegal x");
        if (y == null) throw new IllegalArgumentException("illegal y");
        return x + y;
    }

    @Test
    public void testSumOptional() {
        log.info("【sumOptional: {}】", sumOptional(null, 4));
    }

    private Integer sumOptional(final Integer x, final Integer y) {
        return Optional.ofNullable(x).orElseThrow(() -> new IllegalArgumentException("illegal x"))
                + Optional.ofNullable(y).orElseThrow(() -> new IllegalArgumentException("illegal y"));
    }

    @Test
    public void testOptionalOrElse() {
        final Integer x = 3;
        final Integer y = null;

        // 当为null时，返回0
        log.info("【y: {}】", Optional.ofNullable(y).orElse(0));
    }

    @Test
    public void testOptionalOrElseGet() {
        final Integer x = 3;
        final Integer y = null;

        // 当为null时，返回5
        log.info("【y: {}】", Optional.ofNullable(y).orElseGet(() -> 5));
    }

    @Test
    public void testOptionalIfPresent() {
        final Optional<Integer> x = Optional.ofNullable(3);
        x.ifPresent(integer -> log.info("【ret: {}】", integer));
    }
}
