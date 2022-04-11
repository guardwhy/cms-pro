package com.cms.service.converter;

import com.cms.dao.entity.CmsUserRoleEntity;
import com.cms.service.dto.CmsUserRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author guardwhy
 * @date 2022/4/11 22:26
 */
@Mapper
public interface CmsUserRoleConverter {
    // 拿到CONVERTER
    CmsUserConverter CONVERTER = Mappers.getMapper(CmsUserConverter.class);
    // dto和entity相互转换
    CmsUserRoleEntity dtoToEntity(CmsUserRoleDto dto);
    CmsUserRoleDto entityToDto(CmsUserRoleEntity entity);
}

