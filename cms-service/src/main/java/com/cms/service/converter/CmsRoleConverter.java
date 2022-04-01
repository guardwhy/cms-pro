package com.cms.service.converter;

import com.cms.dao.entity.CmsRoleEntity;
import com.cms.service.dto.CmsRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author guardwhy
 * @date 2022/4/1 21:43
 */
@Mapper
public interface CmsRoleConverter {
    // 拿到CONVERTER
    CmsRoleConverter CONVERTER = Mappers.getMapper(CmsRoleConverter.class);
    // dto和entity相互转换
    CmsRoleEntity dtoToEntity(CmsRoleDto dto);
    CmsRoleDto entityToDto(CmsRoleEntity entity);
}
