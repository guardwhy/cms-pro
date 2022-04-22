package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @author guardwhy
 * @date 2022/4/22 20:51
 * 定时器执行类型
 */
@Getter
public enum TaskExecutionTypeEnum implements BaseEnum {
    EXECUTION_CYCLE(0,"执行周期"),
    EXECUTION_MODE(1,"执行方式");

    private int ordinal;
    private String label;

    TaskExecutionTypeEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
