package com.space.domain.service;

import com.space.db.entity.BlogArticle;

import java.util.List;

public interface BlogArticleService {
    List<BlogArticle> list();

    void save(BlogArticle blogArticle);

    void del(Long id);
}
