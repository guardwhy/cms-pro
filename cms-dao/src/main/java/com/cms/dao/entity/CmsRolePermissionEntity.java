package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/4/1 22:53
 * 角色权限Entity
 */
@Setter
@Getter
public class CmsRolePermissionEntity extends BaseEntity<Integer> {
    private Integer roleId; // 角色id
    private Integer permissionId; // 权限id
}
