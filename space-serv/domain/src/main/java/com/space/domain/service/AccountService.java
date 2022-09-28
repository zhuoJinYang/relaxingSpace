package com.space.domain.service;

import com.space.db.entity.Account;

import java.util.List;

public interface AccountService {
    Account getByUsername(String username);

    List<Account> list();

    void save(Account user);

    void del(Long id);

}
