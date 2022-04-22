package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import com.cms.dao.enums.StaticWebSuffixEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/3/17 12:56
 * 站点信息entity
 */
@Getter
@Setter
public class CmsSiteEntity extends BaseEntity<Integer> {
    private String siteName; // 网络名称
    private String keywords; // 站点关键字
    private String description; // 站点描述
    private Integer staticSuffix;  // 静态页后缀
    private String staticDir;   // 静态页存放目录
    private String tplIndex; // 首页模板路径
    private Boolean staticIndex; // 是否静态化首页
}
