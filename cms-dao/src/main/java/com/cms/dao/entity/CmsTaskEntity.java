package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import com.cms.dao.enums.TaskExecutionCycleUnitEnum;
import com.cms.dao.enums.TaskExecutionTypeEnum;
import com.cms.dao.enums.TaskStaticTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guardwhy
 * @date 2022/4/22 21:08
 * Task任务Entity
 */
@Getter
@Setter
public class CmsTaskEntity extends BaseEntity<Integer> {
    private String name; // 任务名称
    private String code; // quartz任务执行名称
    private TaskStaticTypeEnum type; // 任务类型
    private TaskExecutionTypeEnum taskExecutionType; // 执行周期分类
    private Integer dayOfMonth; // 每月的哪天
    private Integer dayOfWeek; // 周几
    private Integer hour; // 小时
    private Integer minute; // 分钟
    private Integer intervalHour; // 间隔小时
    private Integer intervalMinute; // 间隔分钟
    private String cronExpression; // cron规则表达式
    private Boolean enable; // 是否启用
    private TaskExecutionCycleUnitEnum intervalUnit; // 时间选择
    private String remark;  // 任务说明
}
