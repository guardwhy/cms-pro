package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/3/13 9:14
 */
@Getter
@Setter
public class CmsLogEntity extends BaseEntity<Integer> {
    private Integer userId;
    private String username;
    private String loginIp;
    private String url;
    private String content;
}
