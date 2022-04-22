package com.cms.service.converter;

import com.cms.dao.entity.CmsTaskEntity;
import com.cms.service.dto.CmsTaskDto;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/22 21:16
 * 任务TaskConverter
 */
public interface CmsTaskConverter {
    // 拿到converter
    CmsTaskConverter CONVERTER = Mappers.getMapper(CmsTaskConverter.class);
    // dto和entity相互转换
    CmsTaskEntity dtoToEntity(CmsTaskDto dto);
    CmsTaskDto entityToDto(CmsTaskEntity entity);
    List<CmsTaskDto> entityToDto(List<CmsTaskEntity> entities);
}
