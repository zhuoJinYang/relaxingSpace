package com.space.domain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.db.entity.BlogArticle;
import com.space.db.mapper.BlogArticleMapper;
import com.space.domain.service.BlogArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogArticleServiceImpl implements BlogArticleService {
    @Resource
    private BlogArticleMapper articleMapper;

    @Override
    public List<BlogArticle> list() {
        return articleMapper.selectList(Wrappers.emptyWrapper());
    }
}
