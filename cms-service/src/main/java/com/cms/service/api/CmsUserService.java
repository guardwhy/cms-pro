package com.cms.service.api;

import com.cms.service.dto.CmsUserDto;

/**
 * @author guardwhy
 * @date 2022/3/8 10:59
 * 根据dto来进行查找
 */
public interface CmsUserService {
    /***
     * 根据用户名查找
     * @param username
     * @return
     */
    CmsUserDto selectByUsername(String username);
}
