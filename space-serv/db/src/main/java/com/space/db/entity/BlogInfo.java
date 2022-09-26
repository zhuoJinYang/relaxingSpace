package com.space.db.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zjy
 * @since 2022-09-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("rs_blog_info")
public class BlogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客信息id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户粉丝量
     */
    private Long fans;

    /**
     * 用户简介
     */
    private String introduce;

    /**
     * 逻辑删除标志
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deletedFlag;

    /**
     * 数据添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;

    /**
     * 数据添加人
     */
    @TableField(fill = FieldFill.INSERT)
    private String addMember;

    /**
     * 数据修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime editTime;

    /**
     * 数据修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String editMember;


}
