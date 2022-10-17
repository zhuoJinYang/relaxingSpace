package com.space.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.space.db.dto.BlogDto;
import com.space.db.entity.Blog;
import com.space.domain.model.PageResult;
import lombok.NonNull;

import java.util.List;

public interface BlogService extends IService<Blog> {

    /**
     * 获取博客列表
     * @param page 页数
     * @return 博客列表
     */
    PageResult<Blog> list(Integer page);

    /**
     * 保存博客信息
     * @param blog 博客信息
     * @param content 博客内容
     */
    void save(@NonNull Blog blog, @NonNull String content);

    /**
     * 查看博客信息详情
     * @param id 博客id
     * @return 博客信息
     */
    BlogDto getDetail(@NonNull Long id);

    /**
     * 获取热点数据
     */
    List<Blog> hot();
}
