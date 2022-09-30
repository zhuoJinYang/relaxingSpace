package com.space.admin.web;

import com.space.admin.converter.EntityConverter;
import com.space.admin.converter.VoConverter;
import com.space.admin.vo.BlogVo;
import com.space.db.entity.Blog;
import com.space.domain.service.BlogService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 博客接口
 */
@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @GetMapping("/list")
    public Object list(){
        return VoConverter.INSTANCE.convertBlogArticleList(blogService.list());
    }

    @PostMapping("/save")
    public void add(@RequestBody BlogVo blogVo){
        Blog blog = EntityConverter.INSTANCE.convert(blogVo);
        blogService.save(blog,blogVo.getContent());
    }

    @PostMapping("/del")
    public void delete(@RequestBody @NonNull Long id){
        blogService.del(id);
    }

    @GetMapping("/time")
    public Object time(){
        return LocalDateTime.now();
    }
}
