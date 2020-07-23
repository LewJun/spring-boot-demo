package com.example.lewjun.web;

import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
import com.example.lewjun.domain.Ac01;
import com.example.lewjun.service.Ac01Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ac01s")
@RestController
public class Ac01Controller {

    @Autowired
    private Ac01Service ac01Service;

    /**
     * 获取列表
     *
     * @return List<Ac01>
     */
    @GetMapping
    public List<Ac01> getList() {
        try {
            final int i = 1 / 0;
        } catch (final Exception ex) {
            throw BussException.of(EnumApiResultStatus.FAIL, ex);
        }
        return ac01Service.getList();
    }

    /**
     * 保存Ac01
     *
     * @param ac01 Ac01
     * @return Ac01
     */
    @PostMapping
    public Ac01 postAc01(@RequestBody final Ac01 ac01) {
        ac01Service.save(ac01);
        return ac01;
    }

    /**
     * 根据aac001得到Ac01
     *
     * @param aac001 aac001
     * @return Ac01
     */
    @GetMapping("/{aac001}")
    public Ac01 getAc01(@PathVariable final int aac001) {
        return ac01Service.get(aac001);
    }

    /**
     * 修改Ac01
     *
     * @param ac01 Ac01
     * @return Ac01
     */
    @PutMapping
    public Ac01 putAc01(@RequestBody final Ac01 ac01) {
        final Ac01 oldAc01 = ac01Service.get(ac01.getAac001());
        BeanUtils.copyProperties(ac01, oldAc01);
        ac01Service.update(oldAc01);
        return ac01;
    }

    /**
     * 根据aac001删除Ac01
     *
     * @param aac001 aac001
     * @return 成功返回success
     */
    @DeleteMapping("/{aac001}")
    public String deleteAc01(@PathVariable final int aac001) {
        ac01Service.delete(aac001);
        return "success";
    }

}
