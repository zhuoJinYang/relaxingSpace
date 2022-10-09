package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.space.db.dto.BlogDto;
import com.space.db.entity.Blog;
import com.space.db.entity.BlogContent;
import com.space.db.mapper.BlogMapper;
import com.space.domain.model.PageResult;
import com.space.domain.service.BlogContentService;
import com.space.domain.service.BlogService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogContentService blogContentService;

    @Resource
    private BlogMapper blogMapper;

    @Override
    public PageResult<Blog> list(Integer page) {
        Page<Blog> blogPage = blogMapper.selectPage(new Page<>(page, PageResult.DEFAULT_PAGE_SIZE), Wrappers.emptyWrapper());
        return PageResult.convert(blogPage);
    }

    @Override
    public void save(@NonNull Blog blog, @NonNull String content) {
        if (ObjectUtil.isEmpty(blog.getId())){
            blogMapper.insert(blog);
            blogContentService.save(new BlogContent().setBlogId(blog.getId()).setContent(content));
        }else {
            blogMapper.updateById(blog);
            blogContentService.updateByBlogId(blog.getId(),new BlogContent().setContent(content));
        }
    }

    @Override
    public void del(@NonNull Long id) {
        blogMapper.deleteById(id);
    }

    @Override
    public BlogDto getDetail(@NonNull Long id) {
        return blogMapper.getDetailById(id);
    }
}
