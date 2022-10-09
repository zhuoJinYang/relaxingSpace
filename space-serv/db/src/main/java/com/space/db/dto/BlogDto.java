package com.space.db.dto;

import com.space.db.entity.Blog;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogDto extends Blog {
    private String content;
}
