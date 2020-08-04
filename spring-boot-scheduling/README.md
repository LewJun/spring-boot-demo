# spring-boot-scheduling

> 定时任务

[TOC]

## Timer的使用

* 延迟2000毫秒执行

```java
    @Test
    public void testTimer1() throws InterruptedException {
        System.out.println("1-----> " + LocalDateTime.now());
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("2-----> " + LocalDateTime.now());
            }
        }, 2000);// 延迟两秒后执行

        System.out.println("3-----> " + LocalDateTime.now());
        // 10秒后关闭
        Thread.sleep(10000);

// out:>
//        1-----> 2020-08-04T13:41:42.066
//        3-----> 2020-08-04T13:41:42.067
//        2-----> 2020-08-04T13:41:44.068
    }
```

* 延时后间隔，并取消

```java
    @Test
    public void testTimer2() throws InterruptedException {
        final int[] i = {0};
        System.out.println("1-----> " + LocalDateTime.now());
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("2-----> " + LocalDateTime.now());
                System.out.println("i[0]=" + i[0]);
                if (++i[0] == 4) {
                    // 当 i = 4时，取消定时任务
                    timer.cancel();
                }
            }
        }, 2000, 1500);// 延迟2秒后执行，然后间隔1.5秒继续执行

        System.out.println("3-----> " + LocalDateTime.now());
        // 10秒后关闭
        Thread.sleep(15000);


// out:>
//        1-----> 2020-08-04T14:34:29.795
//        3-----> 2020-08-04T14:34:29.810
//        2-----> 2020-08-04T14:34:31.835
//        i[0]=0
//        2-----> 2020-08-04T14:34:33.353
//        i[0]=1
//        2-----> 2020-08-04T14:34:34.853
//        i[0]=2
//        2-----> 2020-08-04T14:34:36.353
//        i[0]=3
    }
```

## ScheduledExecutorService

> 与Timer很类似，但它的效果更好，多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中有一个因任务报错没有捕获抛出的异常，其它任务便会自动终止运行，使用 ScheduledExecutorService 则可以规避这个问题

```java
    @Test
    public void testScheduledExecutorService() throws InterruptedException {
        final int[] i = {0};
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(LocalDateTime.now());

            if (++i[0] == 4) {
                scheduledExecutorService.shutdown();
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);

        Thread.sleep(15000);
    }
```

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

