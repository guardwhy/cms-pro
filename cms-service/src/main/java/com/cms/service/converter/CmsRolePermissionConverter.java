package com.cms.service.converter;

import com.cms.dao.entity.CmsRolePermissionEntity;
import com.cms.service.dto.CmsRolePermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author guardwhy
 * @date 2022/4/1 22:58
 */
@Mapper
public interface CmsRolePermissionConverter {
    // 拿到CONVERTER
    CmsRolePermissionConverter CONVERTER = Mappers.getMapper(CmsRolePermissionConverter.class);
    // dto和entity相互转换
    CmsRolePermissionEntity dtoToEntity(CmsRolePermissionDto dto);
    CmsRolePermissionDto entityToDto(CmsRolePermissionEntity entity);
}
