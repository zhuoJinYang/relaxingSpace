package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        if (!StrUtil.equals(account.getPassword(),password)){
            throw new ServiceException(ErrorCode.CURRENT_PASSWORD_ERROR);
        }
        account.setPassword(newPassword);
        accountMapper.updateById(account);
    }
}
