package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.StaticWebSuffixEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author guardwhy
 * @date 2022/3/17 12:47
 * 站点dto
 */
@Getter
@Setter
public class CmsSiteDto extends BaseDto<Integer> {
    @NotBlank(message = "请输入站点名称")
    private String siteName; // 网络名称
    @NotBlank(message = "请输入站点关键字")
    private String keywords; // 站点关键字
    @NotBlank(message = "请输入站点描述")
    private String description; // 站点描述
    private StaticWebSuffixEnum staticSuffix;  // 静态页后缀
    private String staticDir;   // 静态页存放目录
    private Boolean staticIndex; // 是否静态化首页
}
