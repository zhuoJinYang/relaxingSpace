package com.space.admin.web;

import cn.hutool.core.util.StrUtil;
import com.space.db.entity.Account;
import com.space.domain.model.LoginResult;
import com.space.domain.service.AuthService;
import com.space.domain.util.RSAUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

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
    public LoginResult login(@Validated @RequestBody LoginParam account){
        return authService.login(account.getUsername(),RSAUtil.passwordDecrypt(account.getPassword()));
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
        if (StrUtil.isNotBlank(token)){
            authService.logout(token);
        }
    }

    @Getter
    @Setter
    static class LoginParam{
        @NotBlank(message = "账号不能为空")
        private String username;
        @NotBlank(message = "密码不能为空")
        private String password;
        private String captchaVerification;
    }
}
