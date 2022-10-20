package com.space.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.space.db.entity.UserFollow;
import lombok.NonNull;

public interface UserFollowService extends IService<UserFollow> {
    void follow(@NonNull Long id,@NonNull Boolean isFollow);
}
