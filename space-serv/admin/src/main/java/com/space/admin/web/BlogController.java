package com.space.admin.web;

import com.space.admin.converter.EntityConverter;
import com.space.admin.converter.VoConverter;
import com.space.admin.vo.BlogVo;
import com.space.db.dto.BlogDto;
import com.space.db.entity.Blog;
import com.space.domain.model.PageResult;
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

    /**
     * 获取博客列表
     */
    @GetMapping("/list")
    public Object list(
            @RequestParam(required = false,defaultValue = "1") Integer page
    ){
        PageResult<Blog> list = blogService.list(page);
        return PageResult.parse(list,VoConverter.INSTANCE.convertBlogArticleList(list.getList()));
    }

    /**
     * TODO 2022/10/13 之后引入redis来做热点数据缓存
     * 获取热点数据
     */
    @GetMapping("/hot")
    public Object hot(){
        return blogService.hot();
    }

    /**
     * 获取博客详情信息
     */
    @GetMapping("/detail")
    public Object detail(
            @RequestParam("id") Long id
    ){
        return VoConverter.INSTANCE.convert(blogService.getDetail(id));
    }

    /**
     * 保存博客信息
     */
    @PostMapping("/save")
    public void add(@RequestBody BlogVo blogVo){
        Blog blog = EntityConverter.INSTANCE.convert(blogVo);
        blogService.save(blog,blogVo.getContent());
    }

    /**
     * 删除博客信息
     */
    @PostMapping("/del")
    public void delete(@RequestBody @NonNull Long id){
        blogService.del(id);
    }
}
