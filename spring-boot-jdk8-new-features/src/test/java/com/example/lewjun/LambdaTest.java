package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LambdaTest {
    @Test
    public void testLambda() {

        final Printer hpPrinter = (msg) -> log.info("【hp print: {}】", msg);
        hpPrinter.print("some msg");

        final Printer epsonPrinter = (msg) -> log.info("【epson printer: {}】", msg);
        epsonPrinter.print("some msg");

        ((Printer) msg -> log.info("【print: {}】", msg)).print("message");

        log.info("【MathOperationAddImpl: {}】", new MathOperationAddImpl().operation(3, 4));
        log.info("【MathOperation add: {}】", new MathOperation() {
            @Override
            public int operation(final int a, final int b) {
                return a + b;
            }
        }.operation(3, 5));

        log.info("【addition: {}】", print(3, 4, (MathOperation) (a, b) -> a + b));
        log.info("【addition: {}】", print(3, 4, (a, b) -> a + b));
        log.info("【addition: {}】", print(3, 4, (int a, int b) -> a + b));
        log.info("【subtraction: {}】", print(3, 4, (a, b) -> a - b));
        log.info("【multiplication : {}】", print(3, 4, (a, b) -> a * b));
        log.info("【division : {}】", print(3, 4, (a, b) -> a / b));
        log.info("【mode: {}】", print(3, 4, (a, b) -> a % b));

        // 不用括号
        final GreetingService greetingService = msg -> log.info("【sayMessage: {}】", msg);
        greetingService.sayMessage("hello");

        // 用括号
        final GreetingService greetingService2 = (msg) -> log.info("【sayMessage: {}】", msg);
        greetingService2.sayMessage("hello");
    }

    private int print(final int a, final int b, final MathOperation operation) {
        return operation.operation(a, b);
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    interface Printer {
        void print(String msg);
    }

    class MathOperationAddImpl implements MathOperation {

        @Override
        public int operation(final int a, final int b) {
            return a + b;
        }
    }
}
