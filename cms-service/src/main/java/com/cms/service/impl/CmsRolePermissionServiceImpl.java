package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.api.CmsRolePermissionService;
import com.cms.service.dto.CmsRolePermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/1 23:05
 * 角色权限业务层实现
 */
@Service
public class CmsRolePermissionServiceImpl implements CmsRolePermissionService {

    // 调用角色权限持久层
    @Autowired
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    @Override
    public void save(CmsRolePermissionDto dto) {

    }

    @Override
    public CmsRolePermissionDto getById(Integer id) {
        return null;
    }

    @Override
    public void update(CmsRolePermissionDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Page<CmsRolePermissionDto> getPage(CmsRolePermissionDto dto) {
        return null;
    }

    @Override
    public List<Integer> getPermissionIdByRoleId(Integer roleId) {
        return cmsRolePermissionMapper.selectPermissionIdByRoleId(roleId);
    }
}