package com.cms.service.converter;

import com.cms.dao.entity.CmsRoleEntity;
import com.cms.service.dto.CmsRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
    // 批量转换
    List<CmsRoleDto> entityToDto(List<CmsRoleEntity> entities);
}
