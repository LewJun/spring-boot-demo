package com.example.lewjun;

import com.example.lewjun.domain.vo.FdQueryParam;
import com.example.lewjun.domain.vo.FdUpdateParam;
import com.example.lewjun.mapper.FdMapper;
import com.example.lewjun.mapper.YyssMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class AppTest {

    @Autowired
    private FdMapper fdMapper;
    @Autowired
    private YyssMapper yyssMapper;

    @Test
    public void findByFd001() {
        log.info("findByFd001: {}", fdMapper.findByFd001("Ss111101"));
    }

    @Test
    public void testFindByParam() {
        final FdQueryParam param = new FdQueryParam();
        param.setFd002("鸡蛋");
        param.setOffset(0);
        param.setLimit(10);
        log.info("testFindByParam: {}", fdMapper.findByParam(param));
    }

    @Test
    public void testUpdateYyss001() {
        final FdUpdateParam param = new FdUpdateParam();
        param.setFd001("Ss111101");
        param.setYyss001("00000000");
        final int ret = fdMapper.updateYyss001(param);
        log.info("ret: {}", ret);
    }

    @Test
    public void testFindByYys001() {
        log.info("findByYys001 {}", yyssMapper.findByYyss001("10010"));
    }

    @Test
    public void testFindByYyss007() {
        log.info("findByYyss007{}", yyssMapper.findByYyss007("鸡蛋"));
    }
}
