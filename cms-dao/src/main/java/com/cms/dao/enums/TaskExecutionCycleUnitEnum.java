package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @author guardwhy
 * @date 2022/4/22 20:55
 * 任务执行周期单位
 */
@Getter
public enum TaskExecutionCycleUnitEnum implements BaseEnum {
    MIN(0,"分"),
    HOUR(1,"时"),
    DAY(2,"每天"),
    WEEK(3,"每周"),
    MONTH(4,"每月");

    private int ordinal;
    private String label;

    TaskExecutionCycleUnitEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
