package com.space.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.db.entity.UserFollow;
import com.space.db.mapper.UserFollowMapper;
import com.space.domain.constant.RedisOption;
import com.space.domain.service.UserFollowService;
import com.space.domain.util.CurrentRequestHolder;
import com.space.domain.util.RedisUtil;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollow> implements UserFollowService {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void follow(@NonNull Long id,@NonNull Boolean isFollow) {
        Long userId = CurrentRequestHolder.getLoginUserId();
        if (isFollow) {
            boolean isSuccess = save(new UserFollow().setUserId(userId).setFollowUserId(id));
            if (isSuccess) {
                redisUtil.sAdd(RedisOption.FOLLOW.getKey(userId.toString()),id);
            }
        } else {
            boolean isSuccess = remove(new QueryWrapper<UserFollow>().eq("user_id", userId)
                    .eq("follow_user_id", id));
            if (isSuccess) {
                redisUtil.sRemove(RedisOption.FOLLOW.getKey(userId.toString()),id);
            }
        }
    }
}
