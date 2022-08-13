package com.rspace.domain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rspace.db.entity.Article;
import com.rspace.db.mapper.ArticleMapper;
import com.rspace.domain.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<Article> list() {
        return articleMapper.selectList(Wrappers.emptyWrapper());
    }
}
