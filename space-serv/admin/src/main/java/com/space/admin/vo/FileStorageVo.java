package com.space.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStorageVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String type;
    private String suffix;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long size;
}
