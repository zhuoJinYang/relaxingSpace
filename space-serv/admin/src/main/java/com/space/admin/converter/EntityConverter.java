package com.space.admin.converter;

import com.space.admin.vo.BlogVo;
import com.space.db.entity.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Vo转Po,Do
 */
@Mapper
public interface EntityConverter {
    EntityConverter INSTANCE = Mappers.getMapper(EntityConverter.class);

    Blog convert(BlogVo record);
}
