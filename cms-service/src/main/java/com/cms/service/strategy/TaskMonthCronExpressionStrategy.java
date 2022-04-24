package com.cms.service.strategy;

import com.cms.service.dto.CmsTaskDto;

/**
 * @author guardwhy
 * @date 2022/4/24 13:57
 * 构建cron表达式策略类【月份】
 */
public class TaskMonthCronExpressionStrategy implements TaskCronExpressionStrategy{
    @Override
    public String buildCronExpress(CmsTaskDto cmsTask) {
        return "0 "+cmsTask.getMinute()+" "+cmsTask.getHour()+" "+ cmsTask.getDayOfMonth()+" * ?";
    }
}
