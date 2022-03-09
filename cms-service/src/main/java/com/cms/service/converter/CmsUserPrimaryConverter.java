package com.cms.service.converter;

import com.cms.dao.entity.CmsUserPrimaryEntity;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author guardwhy
 * @date 2022/3/9 10:40
 * 将entity转换成dto
 */
@Mapper
public interface CmsUserPrimaryConverter {
    // 拿到静态示例
    CmsUserPrimaryConverter CONVERTER = Mappers.getMapper(CmsUserPrimaryConverter.class);
    // 转换
    CmsUserPrimaryDto entityToDto(CmsUserPrimaryEntity entity);
}
