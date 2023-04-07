package com.lsh.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.lsh.utils.SecurityUtils;
import com.lsh.utils.UserHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/06
 */

public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Integer id = null;
        try {
             id = UserHolder.getUser().getId();
        } catch (Exception e) {
            e.printStackTrace();
//            name = -1L;//表示是自己创建
        }
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy", id, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", id, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName(" ", UserHolder.getUser().getId(), metaObject);
    }
}
