package com.example.lewjun.web;

import com.example.lewjun.base.MyBaseController;
import com.example.lewjun.domain.SysDept;
import com.example.lewjun.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/depts")
public class SysDeptController extends MyBaseController<SysDept> {
    @Autowired
    public SysDeptController(final SysDeptService sysDeptService) {
        super(sysDeptService);
    }
}
