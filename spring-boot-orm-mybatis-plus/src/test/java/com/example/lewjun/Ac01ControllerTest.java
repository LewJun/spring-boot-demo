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
