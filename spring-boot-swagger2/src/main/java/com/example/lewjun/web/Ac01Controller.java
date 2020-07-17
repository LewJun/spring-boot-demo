package com.example.lewjun.web;

import com.example.lewjun.domain.Ac01;
import com.example.lewjun.service.Ac01Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Ac01管理")
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
    @ApiOperation("获取列表")
    @GetMapping
    public List<Ac01> getList() {
        return ac01Service.getList();
    }

    /**
     * 保存Ac01
     *
     * @param ac01 Ac01
     * @return Ac01
     */
    @ApiOperation("保存Ac01")
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
    @ApiOperation(value = "根据aac001得到Ac01", notes = "根据url的aac001来获取对象详细信息")
    @ApiImplicitParam(paramType = "path", dataType = "int", name = "aac001", value = "主键", required = true, example = "1")
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
    @ApiOperation("修改Ac01")
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
    @ApiOperation(value = "根据aac001删除Ac01", notes = "根据url的aac001来删除指定对象")
    @ApiImplicitParam(paramType = "path", dataType = "int", name = "aac001", value = "主键", required = true, example = "1")
    @DeleteMapping("/{aac001}")
    public String deleteAc01(@PathVariable final int aac001) {
        ac01Service.delete(aac001);
        return "success";
    }

}
