package com.space.domain.service;

import com.space.domain.model.LoginResult;
import lombok.NonNull;

public interface AuthService {
    LoginResult login(@NonNull String username, @NonNull String password);

    void logout(@NonNull String token);
}
