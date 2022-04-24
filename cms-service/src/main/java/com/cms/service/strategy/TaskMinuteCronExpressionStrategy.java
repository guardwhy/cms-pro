package com.cms.service.strategy;

import com.cms.service.dto.CmsTaskDto;

/**
 * @author guardwhy
 * @date 2022/4/24 13:57
 * 构建cron表达式策略类【分钟】
 */
public class TaskMinuteCronExpressionStrategy implements TaskCronExpressionStrategy{

    @Override
    public String buildCronExpress(CmsTaskDto cmsTask) {
        return "0 */"+cmsTask.getIntervalMinute()+" * * * ?";
    }
}
