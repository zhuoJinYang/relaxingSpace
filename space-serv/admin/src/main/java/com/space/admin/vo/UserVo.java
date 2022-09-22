package com.space.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
}
