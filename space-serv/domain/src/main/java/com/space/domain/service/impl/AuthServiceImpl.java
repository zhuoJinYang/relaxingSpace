package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.space.db.entity.Account;
import com.space.db.entity.Session;
import com.space.domain.constant.Constant;
import com.space.domain.constant.ErrorCode;
import com.space.domain.exception.ServiceException;
import com.space.domain.model.LoginResult;
import com.space.domain.service.AccountService;
import com.space.domain.service.AuthService;
import com.space.domain.service.SessionService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AccountService accountService;
    @Resource
    private SessionService sessionService;
//    @Resource
//    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public LoginResult login(@NonNull String username, @NonNull String password) {
        Account account = accountService.getByUsername(username);
        if (ObjectUtil.isNull(account)){
            throw new ServiceException(ErrorCode.USERNAME_PASSWORD_ERROR);
        }
        if (!StrUtil.equals(account.getPassword(), SecureUtil.sha256(password))){
            throw new ServiceException(ErrorCode.USERNAME_PASSWORD_ERROR);
        }
        if (!StrUtil.equals(account.getStatus(), Constant.YES)) {
            throw new ServiceException(ErrorCode.ACCOUNT_UNAVAILABLE);
        }

        // 根据账号和角色信息，构建会话信息
        Session session = sessionService.createByUserInfo(account);
        sessionService.save(session);
        //存入redis
//        redisUtil.set(RedisOption.TOKEN.getKey(session.getToken()), session, RedisOption.TOKEN.getTimeout());
//        redisUtil.set(RedisOption.ACCOUNT_TOKEN.getKey(session.getLoginUserName() + ":" + session.getToken()),
//                session, RedisOption.ACCOUNT_TOKEN.getTimeout());

        return new LoginResult(session.getToken(),account);
    }

    @Override
    public void logout(@NonNull String token) {
//        String key = RedisOption.TOKEN.getKey(token);
//        if (redisUtil.hasKey(key)){
//            redisUtil.delete(key);
//        }
    }
}
