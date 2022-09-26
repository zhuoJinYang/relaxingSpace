package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.space.db.entity.Session;
import com.space.db.entity.User;
import com.space.domain.constant.ErrorCode;
import com.space.domain.exception.ServiceException;
import com.space.domain.model.LoginResult;
import com.space.domain.service.AuthService;
import com.space.domain.service.SessionService;
import com.space.domain.service.UserService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserService userService;
    @Resource
    private SessionService sessionService;

    @Override
    public LoginResult login(@NonNull String username, @NonNull String password) {
        User userInfo = userService.getByUsername(username);
        if (ObjectUtil.isNull(userInfo)){
            throw new ServiceException(ErrorCode.USERNAME_PASSWORD_ERROR);
        }
        if (!StrUtil.equals(userInfo.getPassword(),password)){
            throw new ServiceException(ErrorCode.USERNAME_PASSWORD_ERROR);
        }

        // 根据账号和角色信息，构建会话信息
        Session session = sessionService.createByUserInfo(userInfo);
        sessionService.save(session);

        return new LoginResult(session.getToken(),userInfo);
    }
}
