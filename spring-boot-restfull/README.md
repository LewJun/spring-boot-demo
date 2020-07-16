# spring-boot-restfull

> restfull 示例

[TOC]

## 目录结构
```
com
  +- example
    +- lewjun
      +- App.java
      |
      +- domain
      |  +- Ac01.java
      |
      +- repository
      |  +- Ac01Repository.java
      |
      +- service
      |  +- Ac01Service.java
      |  +- Ac01ServiceImpl.java
      |
      +- web
      |  +- Ac01Controller.java
      |
```

## [App.java](src/main/java/com/example/lewjun/App.java)

```java
/**
 * spring boot 启动类
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

## [Ac01.java](src/main/java/com/example/lewjun/domain/Ac01.java)

```java
@Data
@Accessors(chain = true)
public class Ac01 extends BaseObj {
    private int aac001;
    private String aac002;
    private String aac003;
    private Integer aac004;
    private LocalDate aac005;
    private int aac006;
    private float aac007;
    private float aac008;
    private List<String> aac009;
    private Ab01 ab01;
    private LocalDateTime aac100;
    private Date aac101;
}
```

## [Ac01Repository.java](src/main/java/com/example/lewjun/repository/Ac01Repository.java)
```java
import com.example.lewjun.domain.Ac01;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Ac01Repository {
    private final Map<Integer, Ac01> repository = Collections.synchronizedMap(new HashMap<>());

    public List<Ac01> getList() {
        return new ArrayList<>(repository.values());
    }

    public void save(final Ac01 ac01) {
        final int nextAac001 = repository.isEmpty() ? 1 : Collections.max(repository.keySet()) + 1;
        ac01.setAac001(nextAac001);
        repository.put(nextAac001, ac01);
    }

    public Ac01 get(final int aac001) {
        return repository.get(aac001);
    }

    public void delete(final int aac001) {
        repository.remove(aac001);
    }

    public void update(final Ac01 ac01) {
        final int aac001 = ac01.getAac001();
        if (repository.containsKey(aac001)) {
            repository.put(aac001, ac01);
        } else {
            throw new IllegalArgumentException("更新失败");
        }
    }
}
```

## [Ac01Service.java](src/main/java/com/example/lewjun/service/Ac01Service.java)
```java
public interface Ac01Service {
    List<Ac01> getList();

    void save(Ac01 ac01);

    Ac01 get(int aac001);

    void delete(int aac001);

    void update(Ac01 ac01);
}
```

## [Ac01ServiceImpl.java](src/main/java/com/example/lewjun/service/Ac01ServiceImpl.java)
```java
@Service
public class Ac01ServiceImpl implements Ac01Service {
    @Autowired
    private Ac01Repository ac01Repository;

    @Override
    public List<Ac01> getList() {
        return ac01Repository.getList();
    }

    @Override
    public void save(final Ac01 ac01) {
        ac01Repository.save(ac01);
    }

    @Override
    public Ac01 get(final int aac001) {
        return ac01Repository.get(aac001);
    }

    @Override
    public void delete(final int aac001) {
        ac01Repository.delete(aac001);
    }

    @Override
    public void update(final Ac01 ac01) {
        ac01Repository.update(ac01);
    }
}
```

## [Ac01Controller.java](src/main/java/com/example/lewjun/web/Ac01Controller.java)
```java

package com.example.lewjun.web;

import com.example.lewjun.domain.Ac01;
import com.example.lewjun.service.Ac01Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ac01s")
@RestController
public class Ac01Controller {

    @Autowired
    private Ac01Service ac01Service;

    /**
     * 获取列表
     *
     * @return List<Ac01>
     */
    @GetMapping
    public List<Ac01> getList() {
        return ac01Service.getList();
    }

    /**
     * 保存Ac01
     *
     * @param ac01 Ac01
     * @return Ac01
     */
    @PostMapping
    public Ac01 postAc01(@RequestBody final Ac01 ac01) {
        ac01Service.save(ac01);
        return ac01;
    }

    /**
     * 根据aac001得到Ac01
     *
     * @param aac001 aac001
     * @return Ac01
     */
    @GetMapping("/{aac001}")
    public Ac01 getAc01(@PathVariable final int aac001) {
        return ac01Service.get(aac001);
    }

    /**
     * 修改Ac01
     *
     * @param ac01 Ac01
     * @return Ac01
     */
    @PutMapping
    public Ac01 putAc01(@RequestBody final Ac01 ac01) {
        final Ac01 oldAc01 = ac01Service.get(ac01.getAac001());
        BeanUtils.copyProperties(ac01, oldAc01);
        ac01Service.update(oldAc01);
        return ac01;
    }

    /**
     * 根据aac001删除Ac01
     *
     * @param aac001 aac001
     * @return 成功返回success
     */
    @DeleteMapping("/{aac001}")
    public String deleteAc01(@PathVariable final int aac001) {
        ac01Service.delete(aac001);
        return "success";
    }

}
```

## [Ac01ControllerTest.java](src/test/java/com/example/lewjun/Ac01ControllerTest.java)
```java

package com.example.lewjun;

import com.example.lewjun.web.Ac01Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ac01ControllerTest {
    private MockMvc mvc;

    @Autowired
    private Ac01Controller ac01Controller;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(ac01Controller)
                .build();
    }

    @Test
    public void testAll() throws Exception {
        RequestBuilder req;

        // 1 查询ac01列表
        req = get("/ac01s");
        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]"))); // 这会儿还是空的

        final String postAc01 = "{\"aac001\":1,\"aac002\":\"KING\",\"aac003\":\"PRESIDENT\",\"aac006\":1,\"aac007\":66.6,\"aac008\":0.0}";
        // 保存Ac01
        req = post("/ac01s")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postAc01);
        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(postAc01)))
        ;

        // 根据aac001得到Ac01
        req = get("/ac01s/1");
        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(postAc01)))
        ;

        final String putAc01 = "{\"aac001\":1,\"aac002\":\"KING\",\"aac003\":\"PRESIDENT\",\"aac006\":2,\"aac007\":66.7,\"aac008\":8.0}";
        // 修改Ac01
        req = put("/ac01s")
                .contentType(MediaType.APPLICATION_JSON)
                .content(putAc01);
        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(putAc01)))
        ;

        // 再次获取列表
        req = get("/ac01s");
        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"aac001\":1,\"aac002\":\"KING\",\"aac003\":\"PRESIDENT\",\"aac006\":2,\"aac007\":66.7,\"aac008\":8.0}]")))
        ;

        // 删除
        req = delete("/ac01s/1");
        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        // 再次获取列表
        req = get("/ac01s");
        mvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }
}
```

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo
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

## postman文件

[spring-boot-restfull.postman_collection.json](doc/spring-boot-restfull.postman_collection.json)