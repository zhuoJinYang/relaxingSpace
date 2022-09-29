package com.space.admin.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityConverter {
    EntityConverter INSTANCE = Mappers.getMapper(EntityConverter.class);

}
