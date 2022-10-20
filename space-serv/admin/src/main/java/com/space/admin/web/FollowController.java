package com.space.admin.web;

import com.space.domain.annotation.CheckToken;
import com.space.domain.service.UserFollowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CheckToken
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    private UserFollowService userFollowService;

    /**
     * 关注用户
     */
    @PutMapping("/{id}/{isFollow}")
    public void follow(@PathVariable("id") Long id,@PathVariable("isFollow") Boolean isFollow){
        userFollowService.follow(id, isFollow);
    }

}
