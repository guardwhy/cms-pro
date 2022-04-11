package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author guardwhy
 * @date 2022/3/10 11:05
 */
@Getter
@Setter
public class CmsUserDto extends BaseDto<Integer> {
    private String username;
    private Boolean status;
    private Boolean admin;
    private String password;
    private String salt;
    private String email;
    private Integer roleId;
    private LocalDateTime registerTime;
}
