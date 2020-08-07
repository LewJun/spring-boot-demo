# spring-boot-mapstruct-lombok mapstruct搭配lombok的使用

## 安装插件

在idea中要安装mapstruct插件

## 添加依赖

```xml
    <dependencies>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
        </dependency>
    </dependencies>
```

## 声明 mapstruct-processor 为 JSR 269 的 Java 注解处理器

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${mapstruct.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

`必须的配置`在以前的基础上引入lombok-processor

```xml
<!-- 引入 lombok-processor -->
<path>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>${lombok.version}</version>
</path>
```

完整[pom.xml](pom.xml)

## Ab01BO

[Ab01BO](src/main/java/com/example/lewjun/domain/bo/Ab01BO.java)
```java
public class Ab01BO extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@TableId注释这就是id
     */
    private Integer aab001;
    /**
     * 部门名称
     */
    private String aab002;
    /**
     * 部门所在位置
     */
    private String aab003;

    // ...setter and getter
}
```

## Ab01DO

[Ab01DO](src/main/java/com/example/lewjun/domain/dataobject/Ab01DO.java)

```java

public class Ab01DO extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@TableId注释这就是id
     */
    private Integer aab001;
    /**
     * 部门名称
     */
    private String aab002;
    /**
     * 部门所在位置
     */
    private String aab003;

    // ...setter and getter
}
```

## 转换

[Ab01Convert.java](src/main/java/com/example/lewjun/domain/convert/Ab01Convert.java)

```java
package com.example.lewjun.domain.convert;

import com.example.lewjun.domain.bo.Ab01BO;
import com.example.lewjun.domain.dataobject.Ab01DO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Ab01Convert {

    Ab01Convert INSTANCE = Mappers.getMapper(Ab01Convert.class);

    Ab01BO convert(Ab01DO ab01DO);
}
```

`convert方法还可以传入多个对象，不仅仅是一个对象。将1个或多个对象的属性转换到目标对象上对应的属性`


编译项目，就会在target/generated-sources/annotations/com.example.lewjun.domain.convert目录下生成Ab01ConvertImpl.java

```java
package com.example.lewjun.domain.convert;

import com.example.lewjun.domain.bo.Ab01BO;
import com.example.lewjun.domain.dataobject.Ab01DO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-07T11:12:04+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_65 (Oracle Corporation)"
)
public class Ab01ConvertImpl implements Ab01Convert {

    @Override
    public Ab01BO convert(Ab01DO ab01DO) {
        if ( ab01DO == null ) {
            return null;
        }

        Ab01BO ab01BO = new Ab01BO();

        ab01BO.setAab001( ab01DO.getAab001() );
        ab01BO.setAab002( ab01DO.getAab002() );
        ab01BO.setAab003( ab01DO.getAab003() );

        return ab01BO;
    }
}
```

## 单元测试

```java
public class JunitTest {
    @Test
    public void test1() {
        Ab01DO ab01DO = new Ab01DO();
        ab01DO.setAab001(111);
        ab01DO.setAab002("aab002");
        ab01DO.setAab003("aab003");

        Ab01BO ab01BO = Ab01Convert.INSTANCE.convert(ab01DO);
        System.out.println(ab01BO.getAab001());
        System.out.println(ab01BO.getAab002());
        System.out.println(ab01BO.getAab003());
    }
}
```

## 使用mapping将两个不同名字的属性对应起来

```java
    /**
     * 将Ab01DO转换为Ab01BO
     *
     * @param ab01DO
     * @return
     */
    @Mappings({
            // 使用mapping将两个不同名字的属性对应起来 ab01BO.setBob( ab01DO.getBirthday() );
            @Mapping(source = "birthday", target = "bob")
    })
    Ab01BO convert(Ab01DO ab01DO);
```

## qualifiedByName

Ab01DO#hobbies中将以逗号分隔的字符串转换为Ab01BO中的List

```java

@Mapper
public interface Ab01Convert {

    Ab01Convert INSTANCE = Mappers.getMapper(Ab01Convert.class);

    /**
     * 将Ab01DO转换为Ab01BO
     *
     * @param ab01DO
     * @return
     */
    @Mappings({
            // 使用mapping将两个不同名字的属性对应起来 ab01BO.setBob( ab01DO.getBirthday() );
            @Mapping(source = "birthday", target = "bob"),
            // 限定 将以逗号分隔的字符串转换为List，ab01BO.setHobbies( translateStringToListString( ab01DO.getHobbies() ) );
            @Mapping(source = "hobbies", target = "hobbies", qualifiedByName = "translateStringToListString")
    })
    Ab01BO convert(Ab01DO ab01DO);

    default List<String> translateStringToListString(final String string) {
        if (string == null) return null;
        return Arrays.asList(string.split(",", -1));
    }
}
```

在Ab01ConvertImpl.java 中，生成的代码是
```java
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-07T13:02:37+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_65 (Oracle Corporation)"
)
public class Ab01ConvertImpl implements Ab01Convert {

    @Override
    public Ab01BO convert(Ab01DO ab01DO) {
        if ( ab01DO == null ) {
            return null;
        }

        Ab01BO ab01BO = new Ab01BO();

        ab01BO.setBob( ab01DO.getBirthday() );
        ab01BO.setHobbies( translateStringToListString( ab01DO.getHobbies() ) );
        ab01BO.setAab001( ab01DO.getAab001() );
        ab01BO.setAab002( ab01DO.getAab002() );
        ab01BO.setAab003( ab01DO.getAab003() );

        return ab01BO;
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

