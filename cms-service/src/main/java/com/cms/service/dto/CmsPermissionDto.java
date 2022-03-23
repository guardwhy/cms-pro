package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author guardwhy
 * @date 2022/3/21 20:23
 */
@Setter
@Getter
public class CmsPermissionDto extends BaseDto<Integer> {
    private Integer parentId; // 父id
    private Boolean menu; // 是否菜单
    private String icon; // 菜单图标
    private String url; // 链接地址
    @NotBlank(message = "请输入权限名称")
    private String name; // 权限名称
    private String action; // 权限码
    @NotNull(message = "请输入排序")
    @Min(value = 0,message = "排序最小只能到0")
    @Max(value = 99999,message = "排序最大只能到99999")
    private Integer priority; // 排序
}
