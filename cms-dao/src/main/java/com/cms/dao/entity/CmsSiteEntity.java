package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/3/17 12:56
 */
@Getter
@Setter
public class CmsSiteEntity extends BaseEntity<Integer> {
    private String siteName; // 网络名称
    private String keywords; // 站点关键字
    private String description; // 站点描述
}
