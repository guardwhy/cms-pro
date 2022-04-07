package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsRoleEntity;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/1 21:47
 * 角色Mapper
 */
public interface CmsRoleMapper extends BaseMapper<CmsRoleEntity, Integer> {
    /***
     * 分页查询
     * @param cmsRoleEntity
     * @return
     */
    List<CmsRoleEntity> selectByPage(CmsRoleEntity cmsRoleEntity);
}
