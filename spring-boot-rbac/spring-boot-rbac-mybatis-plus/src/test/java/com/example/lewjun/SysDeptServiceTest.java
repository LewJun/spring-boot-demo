package com.example.lewjun;

import com.example.lewjun.domain.SysDept;
import com.example.lewjun.domain.SysDeptNode;
import com.example.lewjun.service.SysDeptService;
import com.example.lewjun.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SysDeptServiceTest {

    @Autowired
    SysDeptService sysDeptService;

    @Test
    void getSysDeptTrees() throws JsonProcessingException {
        final SysDeptNode sysDeptTrees = sysDeptService.getSysDeptTrees(2);
        log.info("【getSysDeptTrees: {}】", sysDeptTrees);

        log.info("【json: {}】", JsonUtils.object2String(sysDeptTrees));
    }

    @Test
    void testSave() {
        final SysDept sysDept = new SysDept()
                .setName("Office A")
                .setParentId(1L);
        if (sysDeptService.save(sysDept)) {
            log.info("【sysDept: {}】", sysDept);
        }
    }

    @Test
    void testUpdate() {
        final SysDept sysDept = new SysDept().setId(5L)
                .setName("销售B小组")
                .setParentId(3L);
        sysDeptService.updateById(sysDept);
    }

    @Test
    void testDelete() {
        sysDeptService.removeById(1L);
    }
}
