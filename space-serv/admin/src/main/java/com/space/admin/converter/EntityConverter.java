package com.space.admin.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Vo转Po,Do
 */
@Mapper
public interface EntityConverter {
    EntityConverter INSTANCE = Mappers.getMapper(EntityConverter.class);

}
