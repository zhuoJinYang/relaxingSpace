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

/**
 * <p>
 * 
 * </p>
 *
 * @author zjy
 * @since 2022-09-22
 */
@Getter
@Setter
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 删除标识
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
