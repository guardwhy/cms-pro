package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/3/8 10:26
 */
@Getter
@Setter
public class CmsUserPrimaryEntity extends BaseEntity<Integer> {
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
}
