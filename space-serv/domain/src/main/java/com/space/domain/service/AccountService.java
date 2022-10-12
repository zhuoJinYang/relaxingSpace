package com.space.domain.service;

import com.space.db.entity.Account;
import lombok.NonNull;

import java.util.List;

public interface AccountService {

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
     * 获取账号列表
     *
     * @return 账号列表
     */
    List<Account> list();

    /**
     * 保存账号信息
     *
     * @param user 账号信息
     */
    void save(@NonNull Account user);

    /**
     * 根据帐号id删除账号信息
     *
     * @param id 帐号id
     */
    void del(@NonNull Long id);

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
