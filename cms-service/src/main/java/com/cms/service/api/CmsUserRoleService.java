package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsUserRoleDto;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/11 22:24
 */
public interface CmsUserRoleService extends BaseService<CmsUserRoleDto,Integer> {
    /**
     * 通过用户id查询用户权限
     * @param userId        用户id
     * @return              用户权限
     */
    List<String> selectPermissionsByUserId(Integer userId);
}