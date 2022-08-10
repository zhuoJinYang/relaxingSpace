package com.rspace.domain.constant;

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
}
