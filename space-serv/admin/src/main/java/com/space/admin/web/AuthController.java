package com.space.admin.web;

import com.space.db.entity.Account;
import com.space.domain.model.LoginResult;
import com.space.domain.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthService authService;

    @PostMapping("/login")
    public LoginResult login(@Validated @RequestBody Account account){
        return authService.login(account.getUsername(),account.getPassword());
    }
}
