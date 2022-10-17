package com.space.admin.web;

import com.space.admin.converter.VoConverter;
import com.space.db.entity.Account;
import com.space.domain.service.AccountService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * 账号信息接口
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 获取账号列表
     */
    @GetMapping("/list")
    public Object list(){
        return VoConverter.INSTANCE.convertAccountList(accountService.list());
    }

    /**
     * 保存账号信息
     */
    @PostMapping("/save")
    public void add(@RequestBody Account account){
        accountService.saveAccount(account);
    }

    /**
     * 删除账号信息
     */
    @PostMapping("/del")
    public void del(@RequestBody Long id){
        accountService.removeById(id);
    }

    /**
     * 根据id启动账号
     */
    @PostMapping("/enable")
    public void enable(@RequestBody Account account){
        accountService.enableById(account.getId());
    }

    /**
     * 根据id禁用账号
     */
    @PostMapping("/disable")
    public void disable(@RequestBody Account account){
        accountService.disableById(account.getId());
    }

    /**
     * 修改密码
     */
    @PostMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordParam param){
        accountService.changePassword(param.getId(),param.getPassword(),param.getNewPassword());
    }

    @Getter
    @Setter
    static class ChangePasswordParam{
        @NotBlank(message = "用户id不能为空")
        private Long id;
        @NotBlank(message = "当前密码不能为空")
        private String password;
        @NotBlank(message = "新密码不能为空")
        private String newPassword;
    }
}
