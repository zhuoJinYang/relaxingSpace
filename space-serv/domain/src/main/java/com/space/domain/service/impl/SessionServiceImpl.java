package com.space.domain.service.impl;

import com.space.db.entity.Session;
import com.space.db.entity.User;
import com.space.db.mapper.SessionMapper;
import com.space.domain.constant.ErrorCode;
import com.space.domain.exception.ServiceException;
import com.space.domain.service.SessionService;
import com.space.domain.util.HttpRequestUtil;
import com.space.domain.util.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class SessionServiceImpl implements SessionService {

    @Resource
    private SessionMapper sessionMapper;

    @Override
    public Session createByUserInfo(User userInfo) {
        Session session = new Session();
        HttpServletRequest request = HttpRequestUtil.getRequest();
        session.setLoginUserId(userInfo.getId())
                .setLoginUserName(userInfo.getUsername())
                .setLoginTime(LocalDateTime.now())
                .setToken(JwtUtil.createToken(userInfo))
                .setIp(HttpRequestUtil.getIpAddress(request))
                .setAgent(HttpRequestUtil.getUserAgent(request));
        return session;
    }

    @Override
    public void save(Session session) {
        int insert = sessionMapper.insert(session);
        if (insert <= 0){
            throw new ServiceException(ErrorCode.DATABASE_INSERT_FAIL);
        }
    }
}
