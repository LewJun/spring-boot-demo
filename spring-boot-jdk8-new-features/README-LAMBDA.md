lambda表达式



Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力。

### 定义接口，实现细节由调用者决定
```java
    interface GreetingService {
        void sayMessage(String message);
    }
```

在lambda没有出现之前，实现方式有两种：

1. 定义一个类，实现GreetingService#sayMessage方法

```java
class GreetingServiceImpl implements GreetingService {
    public void sayMessage(String messge) {
        print("hello {}", message);
    }
}
```

new GreetingServiceImpl().sayMessage("your name");

2. 用匿名内部类
```java
new GreetingService(){
    public void sayMessage(String messge) {
        print("hello {}", message);
    }
}("your name");
```

在lambda表达式出现后，就变得很简单了：
```java
((Printer) msg -> log.info("【print: {}】", msg)).print("message");
```

## 打印机

* 接口

```java
    interface Printer {
        void print(String msg);
    }
```

* 实现

统一实现
```java
((Printer) msg -> log.info("【print: {}】", msg)).print("message");
```

hp打印机实现
```java
        final Printer hpPrinter = (msg) -> log.info("【hp print: {}】", msg);
        hpPrinter.print("some msg");
```

epson打印机实现
```java
        final Printer epsonPrinter = (msg) -> log.info("【epson printer: {}】", msg);
        epsonPrinter.print("some msg");
```

## 数学计算

* 接口
```java
    interface MathOperation {
        int operation(int a, int b);
    }
```

* 实现

```java
    private int print(final int a, final int b, final MathOperation operation) {
        return operation.operation(a, b);
    }

// 具体类实现
    class MathOperationAddImpl implements MathOperation {

        @Override
        public int operation(final int a, final int b) {
            return a + b;
        }
    }

// 匿名内部类实现
        log.info("【MathOperation add: {}】", new MathOperation() {
            @Override
            public int operation(final int a, final int b) {
                return a + b;
            }
        }.operation(3, 5));

// lambda实现
        log.info("【addition: {}】", print(3, 4, (MathOperation) (a, b) -> a + b));
        log.info("【addition: {}】", print(3, 4, (a, b) -> a + b));
        log.info("【addition: {}】", print(3, 4, (int a, int b) -> a + b));
        log.info("【subtraction: {}】", print(3, 4, (a, b) -> a - b));
        log.info("【multiplication : {}】", print(3, 4, (a, b) -> a * b));
        log.info("【division : {}】", print(3, 4, (a, b) -> a / b));
        log.info("【mode: {}】", print(3, 4, (a, b) -> a % b));
```