package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guardwhy
 * @date 2022/3/10 11:11
 */
@Getter
@Setter
public class CmsUserEntity extends BaseEntity<Integer> {
    private String username;
    private Boolean status;
    private Boolean admin;
    private String password;
    private String salt;
    private String email;
    private String roleName;
    private LocalDateTime registerTime;
}
