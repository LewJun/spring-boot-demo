package com.example.lewjun.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.lewjun.utils.QueryWrapperUtils;
import org.springframework.web.bind.annotation.*;

public class MyBaseController<T> {
    protected MyIService<T> baseService;

    public MyBaseController(final MyIService<T> baseService) {
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
