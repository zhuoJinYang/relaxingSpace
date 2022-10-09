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
 * @since 2022-09-30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("rs_forum_blog_content")
public class BlogContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客详情id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 博客内容
     */
    private String content;

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
