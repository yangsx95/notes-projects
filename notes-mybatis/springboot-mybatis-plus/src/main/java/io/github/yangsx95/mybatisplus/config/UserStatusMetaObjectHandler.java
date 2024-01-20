package com.yangsx95.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 数据填充，通常用语填充 createTime updateTime等字段
 *
 * @author 杨顺翔
 * @since 2022/07/10
 */
@Component
public class UserStatusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasGetter("status")) {
            this.strictInsertFill(metaObject, "status", Integer.class, 1);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
