package com.space.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogArticleVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Long userId;
    private String title;
    private String label;
    private String summary;
    private String content;
    private Long previews;
    private Long collections;
    private Long likes;
    private Long dislikes;
}
