package com.cms.service.converter;

import com.cms.dao.entity.CmsLogEntity;
import com.cms.service.dto.CmsLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author guardwhy
 * @date 2022/3/13 9:35
 */
@Mapper
public interface CmsLogConverter {
    // 拿到CONVERTER
    CmsLogConverter CONVERTER = Mappers.getMapper(CmsLogConverter.class);
    CmsLogEntity dtoToEntity(CmsLogDto dto);
    CmsLogDto entityToDto(CmsLogEntity entity);
}
