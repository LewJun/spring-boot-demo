package com.example.lewjun;

import com.example.lewjun.domain.SysDeptNode;
import com.example.lewjun.service.SysDeptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SysDeptServiceTest {

    @Autowired
    SysDeptService sysDeptService;

    @Test
    public void getSysDeptTrees() throws JsonProcessingException {
        final SysDeptNode sysDeptTrees = sysDeptService.getSysDeptTrees(2);
        log.info("【getSysDeptTrees: {}】", sysDeptTrees);

        final ObjectMapper objectMapper = new ObjectMapper();
        final String json = objectMapper.writer().writeValueAsString(sysDeptTrees);
        log.info("【json: {}】", json);
    }
}
