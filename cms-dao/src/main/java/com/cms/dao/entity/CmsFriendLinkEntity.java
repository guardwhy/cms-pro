package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/4/15 15:41
 * 友情链接Entity
 */
@Getter
@Setter
public class CmsFriendLinkEntity extends BaseEntity<Integer> {
    private String name;    // 名称
    private String url; // 网站地址
    private Integer priority; // 排列顺序
}
