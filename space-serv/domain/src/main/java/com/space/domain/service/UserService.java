package com.space.domain.service;

import com.space.db.entity.User;

import java.util.List;

public interface UserService {
    User getByUsername(String username);

    List<User> list();

    void save(User user);

    void del(Long id);

}