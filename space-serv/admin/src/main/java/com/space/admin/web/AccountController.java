package com.space.admin.web;

import com.space.admin.converter.VoConverter;
import com.space.db.entity.Account;
import com.space.domain.service.AccountService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
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
    @GetMapping()
    public Object list(){
        return VoConverter.INSTANCE.convertAccountList(accountService.list());
    }

    /**
     * 获取账号信息
     */
    @GetMapping("/{id}")
    public Object getOne(@PathVariable("id") Long id){
        return VoConverter.INSTANCE.convert(accountService.getById(id));
    }

    /**
     * 保存账号信息
     */
    @PostMapping()
    public void add(@RequestBody Account account){
        accountService.saveAccount(account);
    }

    /**
     * 删除账号信息
     */
    @DeleteMapping("/{id}")
    public void del(@PathVariable("id") Long id){
        accountService.removeById(id);
    }

    /**
     * 根据id启动账号
     */
    @PostMapping("/enable/{id}")
    public void enable(@PathVariable("id") Long id){
        accountService.enableById(id);
    }

    /**
     * 根据id禁用账号
     */
    @PostMapping("/disable/{id}")
    public void disable(@PathVariable("id") Long id){
        accountService.disableById(id);
    }

    /**
     * 修改密码
     */
    @PostMapping("/change-password")
    public void changePassword(@Validated @RequestBody ChangePasswordParam param){
        accountService.changePassword(param.getId(),param.getPassword(),param.getNewPassword());
    }

    @Getter
    @Setter
    static class ChangePasswordParam{
        @NotBlank(message = "用户id不能为空")
        @Min(value = 0,message = "请输入正确的id")
        private Long id;
        @NotBlank(message = "当前密码不能为空")
        private String password;
        @NotBlank(message = "新密码不能为空")
        private String newPassword;
    }
}
