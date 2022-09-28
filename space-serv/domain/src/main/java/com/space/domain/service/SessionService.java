package com.space.domain.service;

import com.space.db.entity.Account;
import com.space.db.entity.Session;

public interface SessionService {
    Session createByUserInfo(Account account);

    void save(Session session);
}
