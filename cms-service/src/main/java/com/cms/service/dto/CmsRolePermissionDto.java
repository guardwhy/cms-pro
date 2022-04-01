package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/4/1 22:51
 * 角色权限dto
 */
@Setter
@Getter
public class CmsRolePermissionDto extends BaseDto<Integer> {
    private Integer roleId; // 角色id
    private Integer permissionId; // 权限id
}
