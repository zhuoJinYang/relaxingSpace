package com.space.domain.util;

import cn.hutool.core.util.ReUtil;
import lombok.NonNull;

/**
 * 正则表达式工具
 */
public class RegularUtil {

    /**
     * 校验手机号
     */
    public static boolean verifyMobile(@NonNull String value){
        return ReUtil.isMatch("^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[189]))\\d{8}$", value);
    }
}
