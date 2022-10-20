package com.space.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.db.entity.Account;
import com.space.db.entity.Session;
import com.space.db.mapper.SessionMapper;
import com.space.domain.service.SessionService;
import com.space.domain.util.HttpRequestUtil;
import com.space.domain.util.JwtUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper,Session> implements SessionService {

    @Override
    public Session createByUserInfo(Account userInfo) {
        Session session = new Session();
        HttpServletRequest request = HttpRequestUtil.getRequest();
        session.setLoginUserId(userInfo.getId())
                .setLoginUserName(userInfo.getUsername())
                .setRoleType(userInfo.getRoleType())
                .setLoginTime(LocalDateTime.now())
                .setToken(JwtUtil.createToken(userInfo))
                .setIp(HttpRequestUtil.getIpAddress(request))
                .setAgent(HttpRequestUtil.getUserAgent(request));
        return session;
    }

}
