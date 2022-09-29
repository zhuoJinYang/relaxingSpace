package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.db.entity.Account;
import com.space.db.mapper.AccountMapper;
import com.space.domain.service.AccountService;
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
    public void save(Account account) {
        if (ObjectUtil.isEmpty(account.getId())){
            accountMapper.insert(account);
        }else {
            accountMapper.updateById(account);
        }
    }

    @Override
    public void del(Long id) {
        accountMapper.deleteById(id);
    }
}
