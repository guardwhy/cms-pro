package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsUserEntity;

/**
 * @author guardwhy
 * @date 2022/3/10 11:14
 */
public interface CmsUserMapper extends BaseMapper<CmsUserEntity,Integer> {
    /**
     * 通过用户名查找
     * @param username
     * @return
     */
    CmsUserEntity getByUsername(String username);

    /**
     * 通过邮箱查找
     * @param email   邮箱
     * @return        用户
     */
    CmsUserEntity getByEmail(String email);
}
