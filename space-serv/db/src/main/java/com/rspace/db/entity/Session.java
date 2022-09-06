package com.rspace.db.entity;

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
 * @since 2022-09-06
 */
@Getter
@Setter
@TableName("sys_session")
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long loginUserId;

    private String loginUserName;

    private String loginUserRoleType;

    private LocalDateTime loginTime;

    private String token;

    private String ip;

    private String agent;


}
