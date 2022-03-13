package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;

/**
 * @author guardwhy
 * @date 2022/3/13 9:14
 */
public class CmsLogEntity extends BaseEntity<Integer> {
    private Integer userId;
    private String username;
    private String loginIp;
    private String url;
    private String content;
}
