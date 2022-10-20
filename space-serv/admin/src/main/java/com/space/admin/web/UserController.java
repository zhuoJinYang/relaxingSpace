package com.space.admin.web;

import com.space.db.entity.User;
import com.space.domain.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 博客用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping()
    public Object list(){
        return userService.list();
    }

    @PostMapping()
    public void save(@RequestBody User userInfo){
        userService.save(userInfo);
    }
}
