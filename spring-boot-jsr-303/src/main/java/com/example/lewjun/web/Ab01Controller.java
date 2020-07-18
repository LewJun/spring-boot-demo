package com.example.lewjun.web;

import com.example.lewjun.domain.Ab01;
import com.example.lewjun.service.Ab01Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/ab01s")
@RestController
public class Ab01Controller {

    @Autowired
    private Ab01Service ab01Service;

    /**
     * 获取列表
     *
     * @return List<Ab01>
     */
    @GetMapping
    public List<Ab01> getList() {
        return ab01Service.getList();
    }

    /**
     * 保存Ab01
     *
     * @param ab01 Ab01
     * @return Ab01
     */
    @PostMapping
    public Ab01 postAb01(@RequestBody @Valid final Ab01 ab01) {
        ab01Service.save(ab01);
        return ab01;
    }

    /**
     * 根据aab001得到Ab01
     *
     * @param aab001 aab001
     * @return Ab01
     */
    @GetMapping("/{aab001}")
    public Ab01 getAc01(@PathVariable final int aab001) {
        return ab01Service.get(aab001);
    }

    /**
     * 修改Ab01
     *
     * @param ab01 Ab01
     * @return Ab01
     */
    @PutMapping
    public Ab01 putAc01(@RequestBody @Valid final Ab01 ab01) {
        final Ab01 oldAb01 = ab01Service.get(ab01.getAab001());
        BeanUtils.copyProperties(ab01, oldAb01);
        ab01Service.update(oldAb01);
        return ab01;
    }

    /**
     * 根据aab001删除Ab01
     *
     * @param aab001 aab001
     * @return 成功返回success
     */
    @DeleteMapping("/{aab001}")
    public String deleteAb01(@PathVariable final int aab001) {
        ab01Service.delete(aab001);
        return "success";
    }

}
