package com.cms.service.strategy;

import com.cms.service.dto.CmsTaskDto;

/**
 * @author guardwhy
 * @date 2022/4/24 13:50
 * 构建cron表达式策略类
 */
public interface TaskCronExpressionStrategy {
    /**
     * 构建cron表达式
     * @param cmsTask cmstask
     * @return       cron表达式
     */
    String buildCronExpress(CmsTaskDto cmsTask);
}
