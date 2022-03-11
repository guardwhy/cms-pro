package com.cms.service.converter;

import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.enums.converter.EnumConverter;
import com.cms.service.dto.CmsUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author guardwhy
 * @date 2022/3/10 11:20
 */
@Mapper(uses = EnumConverter.class)
public interface CmsUserConverter {
    CmsUserConverter CONVERTER = Mappers.getMapper(CmsUserConverter.class);

    CmsUserDto entityToDto(CmsUserEntity entity);

}
