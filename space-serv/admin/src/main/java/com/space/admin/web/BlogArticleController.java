package com.space.admin.web;

import com.space.admin.converter.VoConverter;
import com.space.db.entity.BlogArticle;
import com.space.domain.service.BlogArticleService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 博客接口
 */
@RestController
@RequestMapping("/article")
public class BlogArticleController {

    @Resource
    private BlogArticleService blogArticleService;

    @GetMapping("/list")
    public Object list(){
        return VoConverter.INSTANCE.convertBlogArticleList(blogArticleService.list());
    }

    @PostMapping("/save")
    public void add(@RequestBody BlogArticle blogArticle){
        blogArticleService.save(blogArticle);
    }

    @PostMapping("/del")
    public void delete(@RequestBody @NonNull Long id){
        blogArticleService.del(id);
    }

    @GetMapping("/time")
    public Object time(){
        return LocalDateTime.now();
    }
}
