package com.example.lewjun.web;

import com.example.lewjun.domain.vo.YyssQueryParam;
import com.example.lewjun.mapper.YyssMapper;
import com.example.lewjun.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("yyss")
public class YyssController {
    private final YyssMapper yyssMapper;

    @Autowired
    public YyssController(final YyssMapper yyssMapper) {
        this.yyssMapper = yyssMapper;
    }

    @GetMapping("/{yyss001}")
    @ResponseBody
    public String findByFd001(@PathVariable final String yyss001) {
        final Map<String, Object> map = new HashMap<>(2);
        map.put("rows", Collections.singletonList(yyssMapper.findByYyss001(yyss001)));
        map.put("total", 1);
        return JsonUtils.object2String(map);
    }

    @GetMapping("/list/query")
    @ResponseBody
    public String listQuery(final YyssQueryParam param) {
        final Map<String, Object> map = new HashMap<>(2);
        map.put("rows", yyssMapper.findByParam(param));
        map.put("total", yyssMapper.countByParam(param));
        return JsonUtils.object2String(map);
    }
}
