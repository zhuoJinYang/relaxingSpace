package com.space.domain.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.space.domain.util.CurrentRequestHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 需配合实体类中的 @TableField 注解使用
 */
@Component
public class DefaultEntityValueHandler implements MetaObjectHandler {
    // insertFill()和 updateFill()使用条件构造器就会造成自动更新的方法失效
    // 使用strictInsertFill用实体类的方式去更新
    @Override
    public void insertFill(MetaObject metaObject) {
        // 获取线程中用户信息
        Long loginUserId = CurrentRequestHolder.getLoginUserId();

        this.strictInsertFill(metaObject,"deletedFlag",Boolean.class,Boolean.FALSE);
        this.strictInsertFill(metaObject,"addTime", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"addMember", Long.class,loginUserId);
        this.strictInsertFill(metaObject,"editTime", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"editMember", Long.class,loginUserId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 获取线程中用户信息
        Long loginUserId = CurrentRequestHolder.getLoginUserId();
        this.strictInsertFill(metaObject,"editTime", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"editMember", Long.class,loginUserId);
    }
}
