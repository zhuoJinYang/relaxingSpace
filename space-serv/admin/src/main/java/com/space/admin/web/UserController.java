package com.space.admin.web;

import com.space.admin.converter.VoConverter;
import com.space.db.entity.User;
import com.space.domain.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public Object list(){
        return VoConverter.INSTANCE.convertUserList(userService.list());
    }

    @PostMapping("/save")
    public void add(@RequestBody User user){
        userService.save(user);
    }

    @PostMapping("/del")
    public void del(@RequestBody Long id){
        userService.del(id);
    }
}
