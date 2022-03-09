package com.cms.service.impl;

import com.cms.dao.mapper.CmsUserPrimaryMapper;
import com.cms.service.api.CmsUserService;
import com.cms.service.converter.CmsUserPrimaryConverter;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guardwhy
 * @date 2022/3/9 11:08
 */
@Service
public class CmsUserServiceImpl implements CmsUserService {
    // 注入mapper
    @Autowired
    private CmsUserPrimaryMapper cmsUserPrimaryMapper;

    @Override
    public CmsUserPrimaryDto selectByUsername(String username) {
        return CmsUserPrimaryConverter.CONVERTER.entityToDto(cmsUserPrimaryMapper.getByUsername(username));
    }
}
