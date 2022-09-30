package com.space.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.space.db.entity.Blog;
import com.space.db.entity.BlogContent;
import com.space.db.mapper.BlogMapper;
import com.space.domain.service.BlogContentService;
import com.space.domain.service.BlogService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogContentService blogContentService;

    @Resource
    private BlogMapper articleMapper;

    @Override
    public List<Blog> list() {
        return articleMapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public void save(@NonNull Blog blog, @NonNull String content) {
        if (ObjectUtil.isEmpty(blog.getId())){
            articleMapper.insert(blog);
            blogContentService.save(new BlogContent().setBlogId(blog.getId()).setContext(content));
        }else {
            articleMapper.updateById(blog);
            blogContentService.updateByBlogId(blog.getId(),new BlogContent().setContext(content));
        }
    }

    @Override
    public void del(@NonNull Long id) {
        articleMapper.deleteById(id);
    }
}
