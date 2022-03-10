package com.cms.dao.mapper;

import com.cms.dao.entity.CmsUserEntity;

/**
 * @author guardwhy
 * @date 2022/3/10 11:14
 */
public interface CmsUserMapper {
    /***
     * 通过用户名查找
     * @param username
     * @return
     */
    CmsUserEntity getByUsername(String username);
}
