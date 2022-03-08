package com.cms.dao.mapper;

import com.cms.dao.entity.CmsUserPrimaryEntity;

/**
 * @author guardwhy
 * @date 2022/3/8 10:42
 */
public interface CmsUserPrimaryMapper {
    /***
     * 根据名称查询
     * @param username
     * @return
     */
    CmsUserPrimaryEntity getByUsername(String username);
}
