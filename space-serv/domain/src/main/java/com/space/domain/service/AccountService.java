package com.space.domain.service;

import com.space.db.entity.Account;
import lombok.NonNull;

import java.util.List;

public interface AccountService {
    Account getByUsername(String username);

    List<Account> list();

    void save(@NonNull Account user);

    void del(@NonNull Long id);

    void enableById(@NonNull Long id);

    void disableById(@NonNull Long id);

    void changePassword(@NonNull Long id,@NonNull String password,@NonNull String newPassword);
}
