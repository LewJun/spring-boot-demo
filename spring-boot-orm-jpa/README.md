# spring-boot-orm-jpa

> 一个spring boot整合jpa的例子

[TOC]

## 添加依赖
```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
```

## 数据源配置

```yaml
# DataSource Config
spring:
  datasource:
    driver-class-name: org.h2.Driver
    #    schema: classpath:db/schema-h2.sql
    #    data: classpath:db/data-h2.sql
    url: jdbc:h2:mem:test
    username: root
    password: test
  jpa:
    # 显示sql
    show-sql: true
    properties:
      hibernate:
        # 格式化输出sql
        #        format_sql: true
        hbm2ddl:
          auto: update

# 显示jpa的入参
logging:
  level:
    org:
      hibernate:
        type:
          descriptor:
            sql:
              BasicBinder: trace

```

## Ab01

```java
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
public class Ab01 extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@TableId注释这就是id
     */
    @Id
    @GeneratedValue
    private Integer aab001;
    /**
     * 部门名称
     */
    private String aab002;
    /**
     * 部门所在位置
     */
    private String aab003;
}
```

* @Entity标记该类要生成一个表
* @Id标记该字段是一个id
* @GeneratedValue 标记该id自增

## 单元测试

```java
@Slf4j
@SpringBootTest
public class AppTest {
    @Autowired
    private Ab01Repository ab01Repository;

    @Test
    public void testLoadContext() {
        ab01Repository.saveAll(
                Arrays.asList(
                        new Ab01().setAab002("aab002").setAab003("aab003"),
                        new Ab01().setAab002("aab0021").setAab003("aab0031"),
                        new Ab01().setAab002("aab0022").setAab003("aab0032")
                )
        );

        log.info("【findAll: {}】", ab01Repository.findAll());
        log.info("【findAb01: {}】", ab01Repository.findAb01("aab002"));

        log.info("【findByAab002: {}】", ab01Repository.findByAab002("aab002"));

        log.info("【findByAab002AndAab003: {}】", ab01Repository.findByAab002AndAab003("aab0021", "aab0031"));

        ab01Repository.deleteById(2);

        log.info("【findAll: {}】", ab01Repository.findAll(Sort.by(Sort.Order.desc("aab001"))));
    }
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

