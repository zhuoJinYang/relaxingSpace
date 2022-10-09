package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.db.entity.BlogContent;
import com.space.db.mapper.BlogContentMapper;
import com.space.domain.service.BlogContentService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlogContentServiceImpl implements BlogContentService {
    @Resource
    private BlogContentMapper blogContentMapper;

    @Override
    public void save(@NonNull BlogContent blogContext) {
        if (ObjectUtil.isEmpty(blogContext.getId())){
            blogContentMapper.insert(blogContext);
        }else {
            blogContentMapper.updateById(blogContext);
        }
    }

    @Override
    public void updateByBlogId(@NonNull Long blogId, @NonNull BlogContent blogContext) {
        blogContentMapper.update(blogContext,new UpdateWrapper<BlogContent>().eq("blog_id",blogId));
    }
}
