package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.dao.mapper.CmsUserRoleMapper;
import com.cms.service.api.CmsUserRoleService;
import com.cms.service.converter.CmsUserRoleConverter;
import com.cms.service.dto.CmsUserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人微信: 15254124776
 * 个人qq:  2301887641
 */
@Service
public class CmsUserRoleServiceImpl implements CmsUserRoleService {
    // 注入cmsUserRoleMapper
    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;

    /***
     * 保存角色
     * @param dto
     */
    @Override
    public void save(CmsUserRoleDto dto) {
        cmsUserRoleMapper.save(CmsUserRoleConverter.CONVERTER.dtoToEntity(dto));
    }

    /***
     * 根据用户id 查找权限
     * @param userId        用户id
     * @return
     */
    @Override
    public List<String> selectPermissionsByUserId(Integer userId) {
        return cmsUserRoleMapper.selectPermissionsByUserId(userId);
    }
    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsUserRoleDto dto) {

    }

    @Override
    public CmsUserRoleDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsUserRoleDto> getPage(CmsUserRoleDto dto) {
        return null;
    }
}
