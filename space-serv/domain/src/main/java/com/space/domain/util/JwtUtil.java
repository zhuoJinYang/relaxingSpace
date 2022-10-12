package com.space.domain.util;

import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.space.db.entity.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具
 */
@Slf4j
@Getter
@Setter
public class JwtUtil {
    private static final JWTSigner JWT_SIGNER = JWTSignerUtil.hs256("zhuojinyangtianxiadiyishuai".getBytes());

    /**
     * 根据信息创建Token
     */
    public static String createToken(Account userInfo){
        return create("admin",userInfo.getId());
    }

    /**
     * 校验Token是否有效
     */
    public static boolean verifyToken(String token) {
        return JWTUtil.verify(token, JWT_SIGNER);
    }

    private static String create(String client,Long uid){
        Map<String,Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        Map<String, Object> payload = new HashMap<>();
        payload.put("client",client);
        payload.put("uid",uid);
        payload.put("time",System.currentTimeMillis());

        return JWTUtil.createToken(headers,payload,JWT_SIGNER);
    }
}
