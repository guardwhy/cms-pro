package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/4/1 21:36
 * 角色dto
 */
@Getter
@Setter
public class CmsRoleDto extends BaseDto<Integer> {
    private String name; // 权限名称
    private Integer priority; // 排列顺序
    private Boolean all; // 是否所有权限
    private Boolean status; // 状态
}
