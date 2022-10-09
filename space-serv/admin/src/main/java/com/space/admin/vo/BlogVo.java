package com.space.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BlogVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String title;
    private List<String> label;
    private String summary;
    private String content;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long previews;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long collections;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long likes;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dislikes;
}
