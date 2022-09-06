package com.space.domain.util;

import com.space.domain.constant.ErrorCode;
import com.space.domain.constant.RoleType;
import com.space.domain.exception.ServiceException;

/**
 * 请求线程临时数据存储管理
 */
public class CurrentRequestHolder {
    private static final ThreadLocal<Long> LOGIN_USER_ID = new ThreadLocal<>();
    private static final ThreadLocal<RoleType> LOGIN_USER_ROLE_TYPE = new ThreadLocal<>();
    // 普通用户
    // 设置当前线程中的用户id
    public static void setLoginUserId(Long loginUserId){
        LOGIN_USER_ID.set(loginUserId);
    }
    // 获取当前线程中的用户id
    public static Long getLoginUserId(){
        return LOGIN_USER_ID.get();
    }
    public static Long requireLoginUserId(){
        Long loginUserId = LOGIN_USER_ID.get();
        if (loginUserId == null) {
            throw new ServiceException(ErrorCode.USER_NOT_EXIST);
        }
        return loginUserId;
    }
    public static void removeLoginUserId(){
        LOGIN_USER_ID.remove();
    }
    //角色用户
    public static void setLoginUserRoleType(RoleType loginUserRoleType){
        LOGIN_USER_ROLE_TYPE.set(loginUserRoleType);
    }
    public static RoleType getLoginUserRoleType(){
        return LOGIN_USER_ROLE_TYPE.get();
    }
    public static void removeLoginUserRoleType(){
        LOGIN_USER_ROLE_TYPE.remove();
    }
}
