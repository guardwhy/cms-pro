package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/4/1 21:41
 * 角色Entity
 */
@Getter
@Setter
public class CmsRoleEntity extends BaseEntity<Integer> {
    private String name; // 权限名称
    private Integer priority; // 排列顺序
    private Boolean all; // 是否所有权限
    private Boolean status; // 状态
}
