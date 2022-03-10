package com.cms.service.converter;

import com.cms.dao.entity.CmsUserEntity;
import com.cms.service.dto.CmsUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author guardwhy
 * @date 2022/3/10 11:20
 */
@Mapper
public interface CmsUserConverter {
    // 拿到静态示例
    CmsUserConverter CONVERTER = Mappers.getMapper(CmsUserConverter.class);
    // 转换
    CmsUserEntity dtoToEntity(CmsUserDto dto);
    CmsUserDto entityToDto(CmsUserEntity entity);
}
