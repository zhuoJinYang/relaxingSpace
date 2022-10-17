package com.space.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.db.entity.BlogContent;
import com.space.db.mapper.BlogContentMapper;
import com.space.domain.service.BlogContentService;
import org.springframework.stereotype.Service;

@Service
public class BlogContentServiceImpl extends ServiceImpl<BlogContentMapper, BlogContent> implements BlogContentService {

}
