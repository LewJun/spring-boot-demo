package com.example.lewjun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lewjun.domain.Ab01;

import java.util.List;

public interface Ab01Service extends IService<Ab01> {
    List<Ab01> queryByAab002(String aab002);
}
