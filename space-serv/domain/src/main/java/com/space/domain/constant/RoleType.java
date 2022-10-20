package com.space.domain.constant;

import com.space.domain.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 管理权限的枚举类
 */
@Getter
@AllArgsConstructor
public enum RoleType {
    ORDINARY_USER("1","普通用户"),
    VIP_USER("2","vip用户"),
    SUPER_USER("3","至尊用户"),
    SYSTEM_USER("4","管理员")
    ;

    private final String value;
    private final String name;

    public static RoleType of(String value){
        for (RoleType item : values()) {
            if (item.getValue().equals(value)){
                return item;
            }
        }
        throw new ServiceException(ErrorCode.NO_MATCH_ENUM_ITEM);
    }
}
