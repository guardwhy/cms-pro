package com.cms.service.impl;

import com.cms.dao.mapper.CmsLogMapper;
import com.cms.service.api.CmsLogService;
import com.cms.service.converter.CmsLogConverter;
import com.cms.service.dto.CmsLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guardwhy
 * @date 2022/3/13 10:32
 */
@Service
public class CmsLogServiceImpl implements CmsLogService {
    // 注入cmsLogMapper
    @Autowired
    private CmsLogMapper cmsLogMapper;

    @Override
    public void save(CmsLogDto dto) {
        // 保存操作
        cmsLogMapper.save(CmsLogConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsLogDto getById(Integer id) {
        return null;
    }

    @Override
    public void update(CmsLogDto dto) {

    }
}
