package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsRolePermissionDto;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/1 23:03
 * 角色权限业务层
 */
public interface CmsRolePermissionService extends BaseService<CmsRolePermissionDto, Integer> {
    /***
     * 根据角色id 查询权限
     * @param roleId
     * @return
     */
    List<Integer> getPermissionIdByRoleId(Integer roleId);
}
