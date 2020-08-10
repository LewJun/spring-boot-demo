package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

interface DefaultFunctionService {
    float PI = 3.14F;

    default float getArea(final float radius) {
        return PI * radius * radius;
    }

    void nonDefaultFunction();

}

interface Vehicle {
    default void print() {
        System.out.println("我是一辆车");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车");
    }
}

class Car implements Vehicle, FourWheeler {

    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();

        System.out.println("我是一辆车，一辆四轮车。");
    }
}

@Slf4j
public class DefaultFunctionTest {

    @Test
    public void testDefaultFunction() {
        final DefaultFunctionService defaultFunctionService = new DefaultFunctionServiceImpl();
        log.info("【defaultFunctionService: {}】", defaultFunctionService.getArea(2));
        defaultFunctionService.nonDefaultFunction();
    }

    @Test
    public void testCar() {
        Car car = new Car();
        car.print();
    }
}

class DefaultFunctionServiceImpl implements DefaultFunctionService {
    @Override
    public void nonDefaultFunction() {
        System.out.println("nothing");
    }
}