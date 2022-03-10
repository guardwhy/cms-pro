package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @author guardwhy
 * @date 2022/3/10 18:51
 */
@Getter
public enum UserStatusEnum implements BaseEnum {
    NORMAL(1,"正常"),
    DISABLED(2,"禁用"),
    LOCKED(3,"锁定"),
    UNACTIVATED(4,"未激活");

    private int ordinal;
    private String label;

    // 构造器
    UserStatusEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
