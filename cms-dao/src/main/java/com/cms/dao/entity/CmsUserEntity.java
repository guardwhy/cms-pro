package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/3/10 11:11
 */
@Getter
@Setter
public class CmsUserEntity extends BaseEntity<Integer> {
    private String username;
    private Integer status;
    private Boolean admin;
    /**
     * 超级管理员
     */
    private Boolean administrator;

}

