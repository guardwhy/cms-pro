package com.cms.service.impl;

import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.mapper.CmsRoleMapper;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.api.CmsRoleService;
import com.cms.service.converter.CmsRoleConverter;
import com.cms.service.dto.CmsRoleDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/1 22:32
 * 角色实现类
 */
@Service
public class CmsRoleServiceImpl implements CmsRoleService {
    // 注入角色持久层
    @Autowired
    private CmsRoleMapper cmsRoleMapper;
    // 角色权限持久层
    @Autowired
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    /***
     * 角色添加
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CmsRoleDto dto) {
        // 拿到用户entity
        CmsRoleEntity cmsRoleEntity = CmsRoleConverter.CONVERTER.dtoToEntity(dto);
        // 保存操作
        cmsRoleMapper.save(cmsRoleEntity);
        // 如果没有全部获取，则需要进行取反
        if(!dto.getAll()){
            List<Integer> permission = dto.getPermission();
            if(CollectionUtils.isNotEmpty(permission)){
                cmsRolePermissionMapper.batchInsert(permission, cmsRoleEntity.getId());
            }
        }
    }

    @Override
    public CmsRoleDto getById(Integer id) {
        return null;
    }

    @Override
    public void update(CmsRoleDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}