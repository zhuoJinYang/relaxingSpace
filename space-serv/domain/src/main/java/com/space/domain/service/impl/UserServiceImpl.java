package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.db.entity.User;
import com.space.db.mapper.UserMapper;
import com.space.domain.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public void save(User user) {
        if (ObjectUtil.isEmpty(user.getId())){
            userMapper.insert(user);
        }else {
            userMapper.updateById(user);
        }
    }

    @Override
    public void del(Long id) {
        userMapper.deleteById(id);
    }
}
