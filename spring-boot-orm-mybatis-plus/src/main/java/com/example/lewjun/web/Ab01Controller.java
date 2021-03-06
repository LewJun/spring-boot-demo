package com.example.lewjun.web;

import com.example.lewjun.base.MyPageInfo;
import com.example.lewjun.domain.Ab01;
import com.example.lewjun.service.Ab01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ab01Controller {
    @Autowired
    private Ab01Service ab01Service;

    @GetMapping("/ab01")
    public MyPageInfo<Ab01> selectPage(final MyPageInfo<Ab01> ab01Page) {
        return ab01Service.page(ab01Page);
    }
}
