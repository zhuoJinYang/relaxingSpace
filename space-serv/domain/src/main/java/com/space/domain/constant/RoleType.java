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
    SYSTEM_ADMIN("1","系统管理员");

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
