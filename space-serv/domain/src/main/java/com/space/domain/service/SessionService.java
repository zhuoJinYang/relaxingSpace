package com.space.domain.service;

import com.space.db.entity.Session;
import com.space.db.entity.User;

public interface SessionService {
    Session createByUserInfo(User userInfo);

    void save(Session session);
}
