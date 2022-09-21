package com.space.admin.converter;

import com.space.admin.vo.*;
import com.space.db.entity.*;
import com.space.db.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VoConverter {
    VoConverter INSTANCE = Mappers.getMapper(VoConverter.class);

    BlogArticleVo convert(BlogArticle record);
    BlogArticleVo convert(BlogArticleDto record);

    List<BlogArticleVo> convertBlogArticleList(List<BlogArticle> recordList);
    List<BlogArticleVo> convertBlogArticleDtoList(List<BlogArticleDto> recordList);
}
