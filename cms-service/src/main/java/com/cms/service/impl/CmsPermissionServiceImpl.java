package com.cms.service.impl;

import com.cms.dao.mapper.CmsPermissionMapper;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.converter.CmsPermissionConverter;
import com.cms.service.dto.CmsPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/3/21 20:56
 */
@Service
public class CmsPermissionServiceImpl implements CmsPermissionService {
    // 注入权限cmsPermissionMapper
    @Autowired
    private CmsPermissionMapper cmsPermissionMapper;

    @Override
    public void save(CmsPermissionDto dto) {

    }

    @Override
    public CmsPermissionDto getById(Integer id) {
        return null;
    }

    @Override
    public void update(CmsPermissionDto dto) {

    }

    @Override
    public List<CmsPermissionDto> getList(CmsPermissionDto cmsPermissionDto) {
        return CmsPermissionConverter.CONVERTER.entityToDto(cmsPermissionMapper.selectAll());
    }
}
