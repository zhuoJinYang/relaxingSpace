package com.space.admin.web;

import com.space.db.entity.User;
import com.space.domain.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 查询用户列表
     */
    @GetMapping()
    public Object list(){
        return userService.list();
    }

    /**
     * 添加用户信息
     */
    @PostMapping()
    public void save(@RequestBody User userInfo){
        userService.save(userInfo);
    }


}
