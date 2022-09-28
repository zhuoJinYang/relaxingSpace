package com.space.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.space.db.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {
    private String token;
    private LoginUser user;

    public LoginResult(String token, Account account){
        this.token = token;
        this.user = new LoginUser(account);
    }

    @Getter
    @Setter
    public static class LoginUser{
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        private String username;
        public LoginUser(Account account){
            this.id = account.getId();
            this.username = account.getUsername();
        }
    }
}
