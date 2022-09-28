package com.space.admin.web;

import com.space.admin.converter.VoConverter;
import com.space.db.entity.Account;
import com.space.domain.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private AccountService accountService;

    @GetMapping("/list")
    public Object list(){
        return VoConverter.INSTANCE.convertAccountList(accountService.list());
    }

    @PostMapping("/save")
    public void add(@RequestBody Account account){
        accountService.save(account);
    }

    @PostMapping("/del")
    public void del(@RequestBody Long id){
        accountService.del(id);
    }
}
