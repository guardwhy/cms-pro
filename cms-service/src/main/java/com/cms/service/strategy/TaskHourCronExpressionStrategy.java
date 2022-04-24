package com.cms.service.strategy;

import com.cms.service.dto.CmsTaskDto;

/**
 * @author guardwhy
 * @date 2022/4/24 13:54
 * 构建cron表达式策略类【小时】
 */
public class TaskHourCronExpressionStrategy implements TaskCronExpressionStrategy{
    @Override
    public String buildCronExpress(CmsTaskDto cmsTask) {
        return "0 0 */"+cmsTask.getIntervalHour()+" * * ?";
    }
}