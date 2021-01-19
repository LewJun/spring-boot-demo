package com.example.lewjun.web;

import com.example.lewjun.domain.vo.FdQueryParam;
import com.example.lewjun.mapper.FdMapper;
import com.example.lewjun.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fd")
public class FdController {
    private final FdMapper fdMapper;

    @Autowired
    public FdController(final FdMapper fdMapper) {
        this.fdMapper = fdMapper;
    }

    @GetMapping("/list/query")
    @ResponseBody
    public String listQuery( final FdQueryParam param) {
        final Map<String, Object> map = new HashMap<>(2);
        map.put("rows", fdMapper.findByParam(param));
        map.put("total", fdMapper.countByParam(param));
        return JsonUtils.object2String(map);
    }
}
