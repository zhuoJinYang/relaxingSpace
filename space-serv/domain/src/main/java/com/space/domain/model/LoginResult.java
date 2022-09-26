package com.space.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.space.db.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {
    private String token;
    private LoginUser user;

    public LoginResult(String token,User user){
        this.token = token;
        this.user = new LoginUser(user);
    }

    @Getter
    @Setter
    public static class LoginUser{
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        private String username;
        public LoginUser(User user){
            this.id = user.getId();
            this.username = user.getUsername();
        }
    }
}
