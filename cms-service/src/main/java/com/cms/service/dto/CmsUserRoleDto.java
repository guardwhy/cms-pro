package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/4/11 22:28
 */
@Getter
@Setter
public class CmsUserRoleDto extends BaseDto<Integer> {
    private Integer roleId;
    private Integer userId;
}
