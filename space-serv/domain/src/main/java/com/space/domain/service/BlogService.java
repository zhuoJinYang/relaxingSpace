package com.space.domain.service;

import com.space.db.entity.Blog;
import lombok.NonNull;

import java.util.List;

public interface BlogService {
    List<Blog> list();

    void save(@NonNull Blog blog, @NonNull String content);

    void del(@NonNull Long id);
}
