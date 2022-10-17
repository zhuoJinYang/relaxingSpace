package com.space.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.space.db.entity.Account;
import lombok.NonNull;

public interface AccountService extends IService<Account> {

    /**
     * 判断账号是否存在
     *
     * @param username 用户名
     * @return 用户名是否存在
     */
    boolean isUsernameExisted(@NonNull String username);

    /**
     * 根据用户名获取账号
     *
     * @param username 用户名
     * @return 账号信息
     */
    Account getByUsername(String username);

    /**
     * 保存账号信息
     *
     * @param user 账号信息
     */
    void saveAccount(@NonNull Account user);

    /**
     * 根据账号id启用账号
     *
     * @param id 账号id
     */
    void enableById(@NonNull Long id);

    /**
     * 根据账号id禁用账号
     *
     * @param id 账号id
     */
    void disableById(@NonNull Long id);

    /**
     * 修改密码
     *
     * @param id 账号id
     * @param password 旧密码
     * @param newPassword 新密码
     */
    void changePassword(@NonNull Long id,@NonNull String password,@NonNull String newPassword);
}
