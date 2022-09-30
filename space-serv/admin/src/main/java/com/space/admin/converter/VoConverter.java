package com.space.admin.converter;

import com.space.admin.vo.*;
import com.space.db.entity.*;
import com.space.db.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Do,Po转Vo
 */
@Mapper
public interface VoConverter {
    VoConverter INSTANCE = Mappers.getMapper(VoConverter.class);

    BlogVo convert(Blog record);
    BlogVo convert(BlogDto record);
    FileStorageVo convert(FileStorage record);

    List<BlogVo> convertBlogArticleList(List<Blog> recordList);
    List<BlogVo> convertBlogArticleDtoList(List<BlogDto> recordList);
    List<AccountVo> convertAccountList(List<Account> recordList);
}
