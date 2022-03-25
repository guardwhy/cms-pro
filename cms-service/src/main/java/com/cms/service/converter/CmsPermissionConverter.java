package com.cms.service.converter;

import com.cms.dao.entity.CmsPermissionEntity;
import com.cms.service.dto.CmsPermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/3/21 20:39
 */
@Mapper
public interface CmsPermissionConverter {
    // 拿到CONVERTER
    CmsPermissionConverter CONVERTER = Mappers.getMapper(CmsPermissionConverter.class);
    // dto和entity相互转换
    CmsPermissionEntity dtoToEntity(CmsPermissionDto dto);
    CmsPermissionDto entityToDto(CmsPermissionEntity entity);
    List<CmsPermissionDto> entityToDto(List<CmsPermissionEntity> entity);
}
