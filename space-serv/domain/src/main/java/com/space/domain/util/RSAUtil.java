package com.space.domain.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.space.domain.constant.ErrorCode;
import com.space.domain.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class RSAUtil {

    @Value("${custom.security.rsa-password.private-key}")
    private String passwordRsaPrivateKey;

    @Value("${custom.security.rsa-password.public-key}")
    private String passwordRsaPublicKey;

    private static RSA PASSWORD_RSA;

    //@PostConstruct:方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
    @PostConstruct
    private void init(){
        log.info("初始化密码加解密RSA");
        log.info("私钥内容：{}",this.passwordRsaPrivateKey);
        log.info("公钥内容：{}",this.passwordRsaPublicKey);
        PASSWORD_RSA = new RSA(this.passwordRsaPrivateKey,this.passwordRsaPublicKey);
    }

    /**
     * 获取加密公钥
     */
    public static String getPasswordRsaPublicKey(){
        return PASSWORD_RSA.getPublicKeyBase64();
    }

    /**
     * 分组加密
     */
    public static String passwordEncrypt(String password){
        return PASSWORD_RSA.encryptBcd(password, KeyType.PublicKey, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 解密
     */
    public static String passwordDecrypt(String encryptPassword){
        log.info("encryptPassword密码为：--"+encryptPassword);
        if (StrUtil.isBlank(encryptPassword)) {
            throw new ServiceException(ErrorCode.PASSWORD_NULL);
        }
        try {
            return PASSWORD_RSA.decryptStr(encryptPassword,KeyType.PrivateKey, CharsetUtil.CHARSET_UTF_8);
        } catch (RuntimeException e){
            throw new ServiceException(ErrorCode.PASSWORD_NULL);
        }
    }
}
