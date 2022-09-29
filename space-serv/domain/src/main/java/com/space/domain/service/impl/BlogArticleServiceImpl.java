package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.db.entity.BlogArticle;
import com.space.db.mapper.BlogArticleMapper;
import com.space.domain.service.BlogArticleService;
import lombok.NonNull;
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

    @Override
    public void save(BlogArticle blogArticle) {
        if (ObjectUtil.isEmpty(blogArticle.getId())){
            articleMapper.insert(blogArticle);
        }else {
            articleMapper.updateById(blogArticle);
        }
    }

    @Override
    public void del(@NonNull Long id) {
        articleMapper.deleteById(id);
    }
}
