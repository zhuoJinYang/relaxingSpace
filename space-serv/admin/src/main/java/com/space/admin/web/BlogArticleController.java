package com.space.admin.web;

import com.space.admin.converter.VoConverter;
import com.space.db.entity.BlogArticle;
import com.space.domain.service.BlogArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public void delete(@RequestBody Long id){
        blogArticleService.del(id);
    }
}
