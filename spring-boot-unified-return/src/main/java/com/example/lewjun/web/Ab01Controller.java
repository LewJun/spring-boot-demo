package com.example.lewjun.web;

import com.example.lewjun.domain.Ab01;
import com.example.lewjun.service.Ab01Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
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
    public Ab01 postAb01(@RequestBody final Ab01 ab01) {
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
    public Ab01 getAb01(@PathVariable final int aab001) {
        return ab01Service.get(aab001);
    }

    /**
     * 修改Ab01
     *
     * @param ab01 Ab01
     * @return Ab01
     */
    @PutMapping
    public Ab01 putAb01(@RequestBody final Ab01 ab01) {
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
    public void deleteAb01(@PathVariable final int aab001) {
        ab01Service.delete(aab001);
    }

    /**
     * 根据map查询
     *
     * @param map 参数必须添加@RequestParam注解
     * @return
     */
    @GetMapping("/findByMap")
    public Map<String, Object> findByMap(@RequestParam final Map<String, Object> map) {
        log.info("【map:{}】", map);
        return map;
    }

    /**
     * findByMap除了可以用map接收外，还可以使用对象接收。但是对象就不要使用@RequestParam进行注释了
     */
    @GetMapping("/findByObj")
    public Ab01 findByObj(final Ab01 ab01) {
        log.info("【ab01:{}】", ab01);
        return ab01;
    }

    /**
     * url?aab002=xxx
     * 也不需要@RequestParam注解
     */
    @GetMapping("/findByAab002")
    public List<Ab01> findByQueryObj(@RequestParam final String aab002) {
        log.info("【aab002:{}】", aab002);
        final Ab01 ab01 = new Ab01();
        ab01.setAab002(aab002);
        return Arrays.asList(ab01);
    }
}
