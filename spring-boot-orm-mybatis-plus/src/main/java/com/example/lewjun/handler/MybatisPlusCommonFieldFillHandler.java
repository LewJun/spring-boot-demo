package com.example.lewjun.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis plus 通用字段填充
 */
@Component
@Slf4j
public class MybatisPlusCommonFieldFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(final MetaObject metaObject) {
        log.info("start insert fill ....");
        if (metaObject.hasGetter("createTime")) {
            this.strictInsertFill(metaObject, "createTime", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
            /* 上面选其一使用,下面的已过时(注意 strictInsertFill 有多个方法,详细查看源码) */
            //this.setFieldValByName("operator", "Jerry", metaObject);
            //this.setInsertFieldValByName("operator", "Jerry", metaObject);
        }
    }

    @Override
    public void updateFill(final MetaObject metaObject) {
        log.info("start update fill ....");
        if (metaObject.hasGetter("updateTime")) {
            this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
            /* 上面选其一使用,下面的已过时(注意 strictUpdateFill 有多个方法,详细查看源码) */
            //this.setFieldValByName("operator", "Tom", metaObject);
            //this.setUpdateFieldValByName("operator", "Tom", metaObject);
        }
    }

}
