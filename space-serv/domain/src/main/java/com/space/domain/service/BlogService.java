package com.space.domain.service;

import com.space.db.dto.BlogDto;
import com.space.db.entity.Blog;
import com.space.domain.model.PageResult;
import lombok.NonNull;

import java.util.List;

public interface BlogService {
    /**
     * 获取博客列表
     */
    PageResult<Blog> list(Integer page);

    /**
     * 保存博客信息
     */
    void save(@NonNull Blog blog, @NonNull String content);

    /**
     * 删除博客信息
     */
    void del(@NonNull Long id);

    /**
     * 查看博客信息详情
     */
    BlogDto getDetail(@NonNull Long id);
}
