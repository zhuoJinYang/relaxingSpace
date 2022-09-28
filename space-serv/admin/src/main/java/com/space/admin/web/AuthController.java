package com.space.admin.web;

import com.space.db.entity.Account;
import com.space.domain.model.LoginResult;
import com.space.domain.service.AuthService;
import com.space.domain.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthService authService;

    @GetMapping("/password-secret-key")
    public String getPasswordRsaPublicKey(){
        return RSAUtil.getPasswordRsaPublicKey();
    }

    @PostMapping("/login")
    public LoginResult login(@Validated @RequestBody Account account){
        return authService.login(account.getUsername(),RSAUtil.passwordDecrypt(account.getPassword()));
    }
}
