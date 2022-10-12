package com.space.admin.web;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.space.domain.constant.ErrorCode;
import com.space.domain.exception.ServiceException;
import com.space.domain.model.LoginResult;
import com.space.domain.service.AuthService;
import com.space.domain.util.CaptchaUtils;
import com.space.domain.util.RSAUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * 身份验证接口
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthService authService;

    /**
     * 获取公钥
     */
    @GetMapping("/password-secret-key")
    public String getPasswordRsaPublicKey(){
        return RSAUtil.getPasswordRsaPublicKey();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public LoginResult login(@Validated @RequestBody LoginParam account){
        if (!CaptchaUtils.verify(account.getUuid(),account.getCaptcha())){
            throw new ServiceException(ErrorCode.CAPTCHA_VERIFY_ERROR);
        }
        return authService.login(account.getUsername(),RSAUtil.passwordDecrypt(account.getPassword()));
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public void logout(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
        if (StrUtil.isNotBlank(token)){
            authService.logout(token);
        }
    }

    /**
     * 获取验证码
     */
    @GetMapping("/getCaptcha")
    public void getCaptcha(String uuid, HttpServletResponse response) throws IOException {
        switch (RandomUtil.randomInt(3)){
            case 0:
                CaptchaUtils.getLineCaptcha(uuid, response);
                break;
            case 1:
                CaptchaUtils.getCircleCaptcha(uuid, response);
                break;
            case 2:
                CaptchaUtils.getShearCaptcha(uuid, response);
                break;
        }
    }

    @Getter
    @Setter
    static class LoginParam{
        private String uuid;
        @NotBlank(message = "账号不能为空")
        private String username;
        @NotBlank(message = "密码不能为空")
        private String password;
        @NotBlank(message = "验证码不能为空")
        private String captcha;
    }
}
