package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.db.entity.Account;
import com.space.db.mapper.AccountMapper;
import com.space.domain.constant.Constant;
import com.space.domain.constant.ErrorCode;
import com.space.domain.exception.ServiceException;
import com.space.domain.service.AccountService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public boolean isUsernameExisted(@NonNull String username) {
        Long count = query().eq("username", username).count();
        return count > 0;
    }

    @Override
    public Account getByUsername(String username) {
        return query().eq("username", username).one();
    }

    @Override
    public void saveAccount(@NonNull Account account) {
        if (ObjectUtil.isEmpty(account.getId())){
            // 添加新账号前,校验该账号是否存在
            if (this.isUsernameExisted(account.getUsername())){
                throw new ServiceException(ErrorCode.ACCOUNT_USERNAME_EXISTED);
            }

            account.setPassword(SecureUtil.sha256(account.getPassword()));
            account.setStatus(Constant.YES);
            save(account);
        }else {
            updateById(account);
        }
    }

    @Override
    public void enableById(@NonNull Long id) {
        update(new UpdateWrapper<Account>().eq("id",id).set("status", Constant.YES));
    }

    @Override
    public void disableById(@NonNull Long id) {
        update(new UpdateWrapper<Account>().eq("id",id).set("status", Constant.NO));
    }

    @Override
    public void changePassword(@NonNull Long id, @NonNull String password, @NonNull String newPassword) {
        Account account = getById(id);
        if (!StrUtil.equals(account.getPassword(),SecureUtil.sha256(password))){
            throw new ServiceException(ErrorCode.CURRENT_PASSWORD_ERROR);
        }

        boolean success = update().setSql("password = " + SecureUtil.sha256(newPassword)).eq("id", id).update();
        if (!success) {
            throw new ServiceException(ErrorCode.ACCOUNT_PASSWORD_UPDATE_ERROR);
        }
    }
}
