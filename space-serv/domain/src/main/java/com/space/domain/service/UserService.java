package com.space.domain.service;

import com.space.db.entity.User;
import lombok.NonNull;

import java.util.List;

public interface UserService {
    List<User> list();

    void save(@NonNull User userInfo);
}
