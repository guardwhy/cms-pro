package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/3/21 20:36
 */
@Getter
@Setter
public class CmsPermissionEntity extends BaseEntity<Integer> {
    private Integer parentId; // 父id
    private Boolean menu; // 是否菜单
    private String icon; // 菜单图标
    private String name; // 权限名称
    private String action; // 权限码
    private Integer priority; // 排序
}
