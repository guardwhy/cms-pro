package com.cms.service.impl;

import com.cms.dao.mapper.CmsUserMapper;
import com.cms.service.api.CmsUserService;
import com.cms.service.converter.CmsUserConverter;
import com.cms.service.dto.CmsUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guardwhy
 * @date 2022/3/10 11:34
 */
@Service
public class CmsUserServiceImpl implements CmsUserService {
    @Autowired
    private CmsUserMapper cmsUserMapper;

    @Override
    public CmsUserDto selectByUsername(String username) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.getByUsername(username));
    }
}
