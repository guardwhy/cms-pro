package com.cms.service.impl;

import com.cms.dao.mapper.CmsUserPrimaryMapper;
import com.cms.service.api.CmsUserPrimaryService;
import com.cms.service.converter.CmsUserPrimaryConverter;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guardwhy
 * @date 2022/3/10 19:47
 */
@Service
public class CmsUserPrimaryServiceImpl implements CmsUserPrimaryService {
    // 注入cmsUserPrimaryMapper
    @Autowired
    private CmsUserPrimaryMapper cmsUserPrimaryMapper;

    @Override
    public void save(CmsUserPrimaryDto dto) {

    }

    @Override
    public CmsUserPrimaryDto getById(Integer id) {
        return CmsUserPrimaryConverter.CONVERTER.entityToDto(cmsUserPrimaryMapper.selectById(id));
    }

    @Override
    public void update(CmsUserPrimaryDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}