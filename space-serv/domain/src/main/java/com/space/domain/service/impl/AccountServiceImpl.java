package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.db.entity.Account;
import com.space.db.mapper.AccountMapper;
import com.space.domain.constant.Constant;
import com.space.domain.constant.ErrorCode;
import com.space.domain.exception.ServiceException;
import com.space.domain.service.AccountService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public boolean isUsernameExisted(@NonNull String username) {
        Long count = accountMapper.selectCount(
                Wrappers.<Account>lambdaQuery()
                        .eq(Account::getUsername, username));
        return count > 0;
    }

    @Override
    public Account getByUsername(String username) {
        return accountMapper.selectOne(
                Wrappers.<Account> lambdaQuery().eq(Account::getUsername,username)
        );
    }

    @Override
    public List<Account> list() {
        return accountMapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public void save(@NonNull Account account) {
        if (ObjectUtil.isEmpty(account.getId())){
            // 添加新账号前,校验该账号是否存在
            if (this.isUsernameExisted(account.getUsername())){
                throw new ServiceException(ErrorCode.ACCOUNT_USERNAME_EXISTED);
            }
            account.setPassword(SecureUtil.sha256(account.getPassword()));
            account.setStatus(Constant.YES);
            accountMapper.insert(account);
        }else {
            accountMapper.updateById(account);
        }
    }

    @Override
    public void del(@NonNull Long id) {
        accountMapper.deleteById(id);
    }

    @Override
    public void enableById(@NonNull Long id) {
        accountMapper.update(null,new UpdateWrapper<Account>().eq("id",id).set("status", Constant.YES));
    }

    @Override
    public void disableById(@NonNull Long id) {
        accountMapper.update(null,new UpdateWrapper<Account>().eq("id",id).set("status", Constant.NO));
    }

    @Override
    public void changePassword(@NonNull Long id, @NonNull String password, @NonNull String newPassword) {
        Account account = accountMapper.selectById(id);
        if (!StrUtil.equals(account.getPassword(),SecureUtil.sha256(password))){
            throw new ServiceException(ErrorCode.CURRENT_PASSWORD_ERROR);
        }
        account.setPassword(SecureUtil.sha256(newPassword));
        accountMapper.updateById(account);
    }
}
