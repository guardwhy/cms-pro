package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/3/17 12:47
 */
@Getter
@Setter
public class CmsSiteDto extends BaseDto<Integer> {
    private String siteName; // 网络名称
    private String keywords; // 站点关键字
    private String description; // 站点描述
}
