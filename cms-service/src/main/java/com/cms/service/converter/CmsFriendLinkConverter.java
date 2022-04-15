package com.cms.service.converter;

import com.cms.dao.entity.CmsFriendLinkEntity;
import com.cms.service.dto.CmsFriendLinkDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author guardwhy
 * @date 2022/4/15 15:42
 * 友情链接Converter
 */
@Mapper
public interface CmsFriendLinkConverter {
    // 拿到CONVERTER
    CmsFriendLinkConverter CONVERTER = Mappers.getMapper(CmsFriendLinkConverter.class);
    // dto和entity相互转换
    CmsFriendLinkEntity dtoToEntity(CmsFriendLinkDto dto);
    CmsFriendLinkDto entityToDto(CmsFriendLinkEntity entity);
}
