# 函数式接口

> 函数式接口(Functional Interface)就是一个有且只有一个抽象方法，但是可以有多个非抽象方法的接口。

> 函数式接口可以被隐式转换为 lambda 表达式。

> Lambda 表达式和方法引用（实际上也可认为是Lambda表达式）上。

[TOC]

## 定义函数式接口

```java
    @FunctionalInterface // 限定该类最多只有一个抽象方法，超出1个就会报告编译异常
    interface FunctionalInterface1 {
        void print(String msg);
        
        // err
        // void ping();
    }
```

`@FunctionalInterface`限定该类最多只有一个抽象方法，超出1个就会报告编译异常

## 实现

```java
    @Test
    public void testFunctionalInterface() {
        // 匿名内部类实现
        new FunctionalInterface1(){

            @Override
            public void print(String msg) {
                System.out.println("print " + msg);
            }
        }.print("some message");

        // lambda实现
        ((FunctionalInterface1) msg -> System.out.println("print " + msg)).print("some message");

        FunctionalInterface1 functionalInterface1 = (msg -> System.out.println("print " + msg));
        functionalInterface1.print("some message");
    }
```

## jdk 1.8 之前的函数是借口


* java.lang.Runnable

* java.util.concurrent.Callable

* java.security.PrivilegedAction

* java.util.Comparator

* java.io.FileFilter
```java
@FunctionalInterface
public interface FileFilter {

    /**
     * Tests whether or not the specified abstract pathname should be
     * included in a pathname list.
     *
     * @param  pathname  The abstract pathname to be tested
     * @return  <code>true</code> if and only if <code>pathname</code>
     *          should be included
     */
    boolean accept(File pathname);
}
```

* java.io.FilenameFilter
```java
@FunctionalInterface
public interface FilenameFilter {
    /**
     * Tests if a specified file should be included in a file list.
     *
     * @param   dir    the directory in which the file was found.
     * @param   name   the name of the file.
     * @return  <code>true</code> if and only if the name should be
     * included in the file list; <code>false</code> otherwise.
     */
    boolean accept(File dir, String name);
}
```

* java.nio.file.PathMatcher

* java.lang.reflect.InvocationHandler

* java.beans.PropertyChangeListener

* java.awt.event.ActionListener

* javax.swing.event.ChangeListener


jdk 8 新增加的函数式接口

* java.util.function
    * Predicate<T>
        接受一个输入参数，返回一个布尔值结果。英文翻译：断言
    * Consumer<T>
        代表了接受一个输入参数并且无返回的操作。英文翻译：消费者
    * Supplier<T>
        无参数，返回一个结果。英文翻译：供应商、供货商
    * Function<T,R>
        接受一个输入参数，返回一个结果。


```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}


@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}


@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}


@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
```

## Consumer的使用

```java
class FunctionalInterfaceTest {
    @Test public void test() {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null, -100, -200, 300);

        integers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                log.info("【foreach: {}】", integer);
            }
        });


        // 从这里看出，lambda并不直观
        integers.forEach(integer -> log.info("【foreach: {}】", integer));
    }
}
```

Iterable的forEach具体实现：
```java

class Iterable {
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
}
```

判断数字是否为3

```java
class Test {
public void test() {

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

    private void eval(final Integer integer, final Predicate<Integer> predicate) {
        if (predicate.test(integer)) {
            System.out.println("符合要求");
        } else {
            System.out.println("不符合要求");
        }
    }
}
}
```
