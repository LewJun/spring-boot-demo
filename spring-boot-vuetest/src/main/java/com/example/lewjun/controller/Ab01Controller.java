package com.example.lewjun.controller;

import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
import com.example.lewjun.domain.Ab01;
import com.example.lewjun.domain.vo.Ab01SearchInfo;
import com.example.lewjun.repositories.Ab01Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author huiye
 */
@Slf4j
@RestController
@RequestMapping("/ab01s")
public class Ab01Controller {

    @Autowired
    Ab01Repository ab01Repository;

    @GetMapping
    public Page<Ab01> search(final Ab01SearchInfo searchInfo, final Pageable pageable) {
        // 这里的pageable其实是PageRequest
        // 如果查询条件为空，需要传入page和size参数，如果是其它方式传入，可以 PageRequest.of(page, size)
        if (searchInfo == null || StringUtils.isEmpty(searchInfo.getAab002())) {
            final Page<Ab01> ab01Page = ab01Repository.findAll(pageable);
            return ab01Page;
        }
        return ab01Repository.findByAab002(searchInfo.getAab002(), pageable);
    }

    @GetMapping("/{id}")
    public Ab01 findById(@PathVariable final Integer id) {
        return ab01Repository.findById(id).orElseThrow(() -> BussException.of(EnumApiResultStatus.CONTENT_NOT_FOUND));
    }

    @PostMapping
    public Ab01 save(final Ab01 ab01) {
        return ab01Repository.save(ab01);
    }

    @PutMapping
    public Ab01 update(final Ab01 ab01) {
        return ab01Repository.save(ab01);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable final Integer id) {
        try {
            ab01Repository.deleteById(id);
        } catch (final Exception ex) {
            log.error("【出现异常】", ex);
            return 0;
        }
        return 1;
    }

}
