package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/4/11 22:15
 */
@Getter
@Setter
public class CmsUserRoleEntity extends BaseEntity<Integer> {
    private Integer roleId;
    private Integer userId;
}