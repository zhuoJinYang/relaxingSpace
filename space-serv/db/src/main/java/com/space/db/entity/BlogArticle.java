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
@TableName("rs_blog_article")
public class BlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 博客发表者
     */
    private Long userId;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客标签
     */
    private String label;

    /**
     * 博客概述
     */
    private String summary;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 浏览量
     */
    private Long previews;

    /**
     * 收藏量
     */
    private Long collections;

    /**
     * 点赞量
     */
    private Long likes;

    /**
     * 踩量
     */
    private Long dislikes;

    /**
     * 逻辑删除标识
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
     * 数据添加者
     */
    @TableField(fill = FieldFill.INSERT)
    private String addMember;

    /**
     * 数据修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime editTime;

    /**
     * 数据修改者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String editMember;


}
