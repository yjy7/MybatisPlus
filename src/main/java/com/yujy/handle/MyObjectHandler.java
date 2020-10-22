package com.yujy.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yujy
 * @title:
 * @projectName
 * @description:
 * @date 2020/10/2122:19
 */

//交给spring管理
@Component
public class MyObjectHandler implements MetaObjectHandler {


    /**
     * 使用mp实现添加操作，执行此方法
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);

        this.setFieldValByName("version",1,metaObject);
        this.setFieldValByName("deleted",0,metaObject);

    }

    /**
     * 使用mp实现修改操作，执行此方法
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
