package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsUserRoleEntity;

/**
 * @author guardwhy
 * @date 2022/4/11 22:18
 */
public interface CmsUserRoleMapper extends BaseMapper<CmsUserRoleEntity,Integer> {
    /**
     * 通过用户id查询角色id
     * @param userId     用户id
     * @return
     */
    Integer selectByUserId(Integer userId);
}
