package com.lsh.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.lsh.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/06
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
//        try {
        //todo 得到用户的信息
//            userId = SecurityUtils.getUserId();
//        } catch (Exception e) {
//            e.printStackTrace();
//            userId = -1L;//表示是自己创建
//        }
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        // TODO: 2023/3/29
//        this.setFieldValByName(" ", SecurityUtils.getUserId(), metaObject);
    }
}
