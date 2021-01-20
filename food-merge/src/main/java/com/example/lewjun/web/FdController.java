package com.example.lewjun.web;

import com.example.lewjun.domain.vo.FdQueryParam;
import com.example.lewjun.domain.vo.FdUpdateParam;
import com.example.lewjun.mapper.FdMapper;
import com.example.lewjun.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @GetMapping("/{fd001}")
    @ResponseBody
    public String findByFd001(@PathVariable final String fd001) {
        final Map<String, Object> map = new HashMap<>(2);
        map.put("rows", Collections.singletonList(fdMapper.findByFd001(fd001)));
        map.put("total", 1);
        return JsonUtils.object2String(map);
    }

    @GetMapping("/list/query")
    @ResponseBody
    public String listQuery(final FdQueryParam param) {
        final Map<String, Object> map = new HashMap<>(2);
        map.put("rows", fdMapper.findByParam(param));
        map.put("total", fdMapper.countByParam(param));
        return JsonUtils.object2String(map);
    }

    @PostMapping("/merge")
    @ResponseBody
    public String merge(final FdUpdateParam fdUpdateParam) {
        return JsonUtils.object2String(fdMapper.updateYyss001(fdUpdateParam));
    }
}
