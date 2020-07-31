# spring-boot-redis-lettuce

> 一个spring boot整合lettuce redis的例子

> `Lettuce` 和 `Jedis` 的都是连接Redis Server的客户端程序。`Jedis`在实现上是直连redis server，多线程环境下非线程安全，除非使用连接池，为每个Jedis实例增加物理连接。Lettuce基于Netty的连接实例（StatefulRedisConnection），可以在多个线程间并发访问，且线程安全，满足多线程环境下的并发访问，同时它是可伸缩的设计，一个连接实例不够的情况也可以按需增加连接实例。


[TOC]

## 添加依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!--
    redis连接池配置需要依赖commons-pool2，否则会报错
    nested exception is java.lang.NoClassDefFoundError: org/apache/commons/pool2/impl/GenericObjectPoolConfig
    -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-pool2</artifactId>
    </dependency>
</dependencies>
```


## redis配置

```yaml
spring:
  redis:
    host: 127.0.0.1
    password: foobared
    # 连接超时时间（毫秒）
    timeout: 10000
    # Redis默认情况下有16个分片，这里配置具体使用的分片，默认是0 select 0
    database: 0
    # redis连接池配置
    lettuce:
      pool:
        # 连接池最大连接数（使用负值表示没有限制） 默认 8
        max-active: 8
        # 连接池最大阻塞等待时间（使用负值表示没有限制） 默认 -1
        max-wait: -1
        # 连接池中的最大空闲连接 默认 8
        max-idel: 8
        # 连接池中的最小空闲连接 默认 0
        min-idel: 0
```

## 启动 redis-server

```shell script
./redis-server.exe redis.windows.conf
```


## 测试

[AppTest.java](src/test/java/com/example/lewjun/AppTest.java)

```java
/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class AppTest {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testLoadContext() {
        final ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("uname", "zs");
        final String uname = stringStringValueOperations.get("uname");
        log.info("【uname: {}】", uname);

        redisTemplate.opsForValue().set("myname", "ls");

        redisTemplate.opsForValue().set("ab01",
                Ab01.builder()
                        .aab001(10)
                        .aab002("aab002")
                        .aab003("aab003")
                        .build()
        );
        final Ab01 ab01 = (Ab01) redisTemplate.opsForValue().get("ab01");
        log.info("【ab01: {}】", ab01);
    }
}
```

结果报错了

```log
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 
'com.example.lewjun.AppTest': Unsatisfied dependency expressed through field 'redisTemplate'; 
nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 
'org.springframework.data.redis.core.RedisTemplate<java.lang.String, java.io.Serializable>' available: expected at 
least 1 bean which qualifies as autowire candidate. Dependency annotations: 
{@org.springframework.beans.factory.annotation.Autowired(required=true)}
```

RedisTemplate模板默认只支持RedisTemplate<String, String>，也就是只能存入字符串值，StringRedisTemplate就可以存入字符串。

StringRedisTemplate.java
```java
public class StringRedisTemplate extends RedisTemplate<String, String> {
    public StringRedisTemplate() {
        this.setKeySerializer(RedisSerializer.string());
        this.setValueSerializer(RedisSerializer.string());
        this.setHashKeySerializer(RedisSerializer.string());
        this.setHashValueSerializer(RedisSerializer.string());
    }

    public StringRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
```

## 自定义Template

[RedisConfig.java](src/main/java/com/example/lewjun/config/RedisConfig.java)

```java
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Serializable> serializableRedisTemplate(final LettuceConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        // key 用String来表示
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // value 使用jackson来转换为json
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
```

保存字符串就还是用以前的redisTemplate或stringRedisTemplate，不要用serializableRedisTemplate保存string字符串，
否则得到的数据是"\"zs\""，因为被GenericJackson2JsonRedisSerializer序列化了。
```java
@Slf4j
@SpringBootTest
public class AppTest {
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testLoadContext() {
        final ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("uname", "zs");
        final String uname = stringStringValueOperations.get("uname");
        log.info("【uname: {}】", uname);

        redisTemplate.opsForValue().set("myname", "ls");

        serializableRedisTemplate.opsForValue().set("ab01",
                Ab01.builder()
                        .aab001(10)
                        .aab002("aab002")
                        .aab003("aab003")
                        .build()
        );
        final Ab01 ab01 = (Ab01) serializableRedisTemplate.opsForValue().get("ab01");
        log.info("【ab01: {}】", ab01);
    }
}
```

此时就不会报错了。

登陆redis-cli.exe客户端，通过`get ab01`会得到如下结果：

```log
127.0.0.1:6379> get ab01
"{\"@class\":\"com.example.lewjun.domain.Ab01\",\"aab001\":10,\"aab002\":\"aab002\",\"aab003\":\"aab003\"}"
```

## 其它类型

*    opsForValue： 对应 String（字符串）
*    opsForZSet： 对应 ZSet（有序集合）
*    opsForHash： 对应 Hash（哈希）
*    opsForList： 对应 List（列表）
*    opsForSet： 对应 Set（集合）
*    opsForGeo： 对应 GEO（地理位置）

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

