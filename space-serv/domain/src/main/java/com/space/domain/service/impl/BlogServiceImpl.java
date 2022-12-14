package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.db.dto.BlogDto;
import com.space.db.entity.Blog;
import com.space.db.entity.BlogContent;
import com.space.db.mapper.BlogMapper;
import com.space.domain.constant.RedisOption;
import com.space.domain.model.PageResult;
import com.space.domain.service.BlogContentService;
import com.space.domain.service.BlogService;
import com.space.domain.util.CacheUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogMapper,Blog> implements BlogService {

    @Resource
    private BlogContentService blogContentService;

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private CacheUtils cacheUtil;

    @Override
    public PageResult<Blog> list(Integer page) {
        Page<Blog> blogPage = page(new Page<>(page, PageResult.DEFAULT_PAGE_SIZE), Wrappers.emptyWrapper());
        return PageResult.convert(blogPage);
    }

    @Override
    public void save(@NonNull Blog blog, @NonNull String content) {
        if (ObjectUtil.isEmpty(blog.getId())){
            save(blog);
            blogContentService.save(new BlogContent().setBlogId(blog.getId()).setContent(content));
        }else {
            updateById(blog);
            blogContentService.update().setSql("content = " + content).eq("blog_id",blog.getId()).update();
        }
    }

    @Override
    public BlogDto getDetail(@NonNull Long id) {
        return cacheUtil.queryWithLogicalExpire(RedisOption.BLOG.getKey(), id, BlogDto.class, blogMapper::getDetailById, RedisOption.BLOG.getTimeout(), TimeUnit.SECONDS);
    }

    @Override
    public List<Blog> hot() {
        return list(Wrappers.lambdaQuery(Blog.class).orderByDesc(Blog::getPreviews).last("limit 5"));
    }
}
