package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/3/21 20:23
 */
@Setter
@Getter
public class CmsPermissionDto extends BaseDto<Integer> {
    private Integer parentId; // 父id
    private Boolean menu; // 是否菜单
    private String icon; // 菜单图标
    private String name; // 权限名称
    private String action; // 权限码
}
