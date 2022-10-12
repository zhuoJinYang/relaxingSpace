package com.space.domain.service;

import com.space.domain.model.LoginResult;
import lombok.NonNull;

public interface AuthService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 登录用户信息
     */
    LoginResult login(@NonNull String username, @NonNull String password);

    /**
     * 登出
     * @param token 登录用户信息
     */
    void logout(@NonNull String token);
}
