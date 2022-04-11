package com.cms.service.converter;

import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.enums.converter.EnumConverter;
import com.cms.service.dto.CmsUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/3/10 11:20
 */
@Mapper(uses = EnumConverter.class)
public interface CmsUserConverter {
    // 拿到CONVERTER
    CmsUserConverter CONVERTER = Mappers.getMapper(CmsUserConverter.class);
    // dto和entity相互转换
    CmsUserEntity dtoToEntity(CmsUserDto dto);
    CmsUserDto entityToDto(CmsUserEntity entity);
    List<CmsUserDto> entityToDto(List<CmsUserEntity> entity);
}
