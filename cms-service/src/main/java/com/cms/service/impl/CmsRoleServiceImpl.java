package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.mapper.CmsRoleMapper;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.api.CmsRoleService;
import com.cms.service.converter.CmsRoleConverter;
import com.cms.service.dto.CmsRoleDto;
import com.github.pagehelper.PageHelper;
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

    /***
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public Page<CmsRoleDto> getPage(CmsRoleDto dto) {
        // 获取pageInfo
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        // 拿到分页数据
        SearchProvider of = SearchProvider.of(CmsRoleConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsRoleEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).
                doSelectPage(() -> cmsRoleMapper.selectBySearchProvider(of));
        // 返回最终页数
        return new Page<>(page.getTotal(),CmsRoleConverter.CONVERTER.entityToDto(page.getResult()));
    }
}