package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @author guardwhy
 * @date 2022/3/23 17:19
 * 权限枚举
 */
@Getter
public enum PermissionTypeEnum implements BaseEnum {
    BUTTON(0, "按钮"),
    MENU(1, "菜单");

    private int ordinal;
    private String label;

    PermissionTypeEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
