package com.space.admin.web;

import com.space.domain.service.BlogArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private BlogArticleService articleService;

    @GetMapping("/list")
    public Object list(){
        return articleService.list();
    }
}
