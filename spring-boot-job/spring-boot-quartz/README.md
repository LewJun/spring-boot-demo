# spring-boot-quartz


> quartz 定时任务
我们要实现定时任务的高可用，需要部署多个 JVM 进程，
如果是spring task，那么会导致多个JVM进程同时启动定时任务，出现重复。
Quartz 自带了集群方案，它通过将作业信息存储到关系数据库中，并使用关系数据库的行锁来实现执行作业的竞争，从而保证多个进程下，同一个任务在相同时刻，不能重复执行。
在生产环境下，一定一定一定要使用 Quartz 的集群模式，保证定时任务的高可用。

[TOC]

## 添加quartz依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
```

## 1. 定义job，实现逻辑

[SayHelloJob.java](src/main/java/com/example/lewjun/job/SayHelloJob.java)

```java
@Slf4j
public class SayHelloJob extends QuartzJobBean {
    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        log.info("【sayHelloJob: {}】", context);
    }
}
```

## 2. 配置job

[QuartzConfig.java](src/main/java/com/example/lewjun/config/QuartzConfig.java)

```java
@Configuration
public class QuartzConfig {
    /**
     * 配置SayHelloJob
     */
    public static class SayHelloJobConfig {
        @Value("${spring.quartz.cron.sayHelloJob}")
        private String cronSayHelloJob;

        /**
         * 注入SayHelloJob
         */
        @Bean
        public JobDetail sayHelloJob() {
            return JobBuilder.newJob(SayHelloJob.class)
                    .withIdentity("sayHelloJob")
                    .storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                    .build();
        }

        /**
         * 定义SayHelloJob的触发器
         */
        @Bean
        public Trigger sayHelloJobTrigger() {
            return TriggerBuilder.newTrigger()
                    .withIdentity("sayHelloJobTrigger")
                    .forJob(sayHelloJob()) // 对应job为sayHelloJob
                    .withSchedule(
//                            SimpleScheduleBuilder.simpleSchedule()
//                                    .withIntervalInSeconds(5)
//                                    .repeatForever()
                            CronScheduleBuilder.cronSchedule(cronSayHelloJob)
                    )
                    .build();
        }
    }
}
```

## application.yml

```yaml
spring:
  # Quartz 的配置，对应 QuartzProperties 配置类。
  quartz:
    job-store-type: MEMORY
    auto-startup: true
    startup-delay: 0
    scheduler-name: quartzScheduler
    wait-for-jobs-to-complete-on-shutdown: true
    overwrite-existing-jobs: false
    properties:
      org:
        quartz:
          threadPool:
            threadCount: 25
            threadPriority: 5 # 线程优先级
            class: org.quartz.simpl.SimpleThreadPool # 线程池类型
    cron:
      sayHelloJob: 0/5 * * * * ?
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

