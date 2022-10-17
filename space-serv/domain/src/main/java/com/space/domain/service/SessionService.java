package com.space.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.space.db.entity.Account;
import com.space.db.entity.Session;

public interface SessionService extends IService<Session> {

    /**
     * 根据用户信息创建session
     * @param account 用户信息
     * @return session
     */
    Session createByUserInfo(Account account);
}
