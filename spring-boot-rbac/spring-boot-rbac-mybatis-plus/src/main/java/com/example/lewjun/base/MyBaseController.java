package com.example.lewjun.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.lewjun.domain.BaseObj;
import com.example.lewjun.utils.QueryWrapperUtils;
import org.springframework.web.bind.annotation.*;

public abstract class MyBaseController<T extends BaseObj, S extends MyIService<T>> {
    protected S baseService;

    public MyBaseController(final S baseService) {
        this.baseService = baseService;
    }

    @GetMapping
    public IPage<T> list(final MyPageInfo<T> pageInfo, final T t) {
        return baseService.page(pageInfo, QueryWrapperUtils.initQueryWrapper(t));
    }

    @PostMapping
    public boolean create(final T t) {
        return baseService.save(t);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable(name = "id") final Integer id) {
        return baseService.removeById(id);
    }

    @PutMapping
    public boolean update(final T t) {
        return baseService.updateById(t);
    }
}
