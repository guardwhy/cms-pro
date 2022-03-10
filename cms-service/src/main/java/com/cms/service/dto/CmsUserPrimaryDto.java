package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/3/9 10:31
 * 将po转换成dto
 */
@Getter
@Setter
public class CmsUserPrimaryDto extends BaseDto<Integer> {
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
}
