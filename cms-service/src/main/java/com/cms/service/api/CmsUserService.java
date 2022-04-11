package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsUserDto;

/**
 * @author guardwhy
 * @date 2022/3/8 10:59
 * 根据dto来进行查找
 */
public interface CmsUserService extends BaseService<CmsUserDto,Integer> {

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    CmsUserDto selectByUsername(String username);

    /**
     * 根据邮箱查询
     * @param email   邮箱
     * @return
     */
    CmsUserDto selectByEmail(String email);
}
