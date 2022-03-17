package com.cms.service.converter;

import com.cms.dao.entity.CmsSiteEntity;
import com.cms.service.dto.CmsSiteDto;
import org.mapstruct.factory.Mappers;

/**
 * @author guardwhy
 * @date 2022/3/17 13:00
 */
public interface CmsSiteConverter {
    // 拿到CONVERTER
    CmsSiteConverter CONVERTER = Mappers.getMapper(CmsSiteConverter.class);
    // dto和entity相互转换
    CmsSiteEntity dtoToEntity(CmsSiteDto dto);
    CmsSiteDto entityToDto(CmsSiteEntity entity);
}
