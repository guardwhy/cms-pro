package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/4/15 15:36
 * 友情链接Dto
 */
@Getter
@Setter
public class CmsFriendLinkDto extends BaseDto<Integer> {
    private String name;    // 名称
    private String url; // 网站地址
    private Integer priority; // 排列顺序
}
