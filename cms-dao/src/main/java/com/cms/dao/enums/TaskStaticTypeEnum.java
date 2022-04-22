package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * @author guardwhy
 * @date 2022/4/22 21:00
 * 任务类型
 */
@Getter
public enum TaskStaticTypeEnum implements BaseEnum {
    INDEX(0,"首页静态化"),
    CHANNEL(1,"栏目静态化"),
    CONTENT(2,"内容静态化");

    private int ordinal;
    private String label;

    TaskStaticTypeEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
