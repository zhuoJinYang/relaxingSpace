package com.space.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("sys_session")
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 访问id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 访问用户id
     */
    private Long loginUserId;

    /**
     * 访问用户名称
     */
    private String loginUserName;

    /**
     * 访问用户角色
     */
    private String loginUserRoleType;

    /**
     * 访问时间
     */
    private LocalDateTime loginTime;

    /**
     * token
     */
    private String token;

    /**
     * 访问ip
     */
    private String ip;

    /**
     * 访问代理
     */
    private String agent;


}
