package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsString;
import com.cms.core.exception.BusinessException;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsTaskEntity;
import com.cms.dao.enums.TaskExecutionCycleUnitEnum;
import com.cms.dao.enums.TaskExecutionTypeEnum;
import com.cms.dao.enums.TaskStaticTypeEnum;
import com.cms.dao.mapper.CmsTaskMapper;
import com.cms.service.api.CmsTaskService;
import com.cms.service.converter.CmsTaskConverter;
import com.cms.service.dto.CmsTaskDto;
import com.cms.service.strategy.*;
import com.cms.service.task.job.IndexStaticJob;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/4/22 21:45
 * 任务Task业务实现类
 */
@Service
@Slf4j
public class CmsTaskServiceImpl implements CmsTaskService {
    // 注入定时器mapper
    @Autowired
    private CmsTaskMapper cmsTaskMapper;
    // 注入调度器
    @Autowired
    private Scheduler scheduler;

    /***
     * 策略map
     */
    private static final Map<TaskExecutionCycleUnitEnum, TaskCronExpressionStrategy> TASK_CRON_EXPRESSION = new HashMap<TaskExecutionCycleUnitEnum,
            TaskCronExpressionStrategy>(){{
        put(TaskExecutionCycleUnitEnum.MIN, new TaskMinuteCronExpressionStrategy());
        put(TaskExecutionCycleUnitEnum.HOUR, new TaskHourCronExpressionStrategy());
        put(TaskExecutionCycleUnitEnum.DAY, new TaskDayCronExpressionStrategy());
        put(TaskExecutionCycleUnitEnum.WEEK, new TaskWeekCronExpressionStrategy());
        put(TaskExecutionCycleUnitEnum.MONTH, new TaskMonthCronExpressionStrategy());
    }};

    /**
     *执行具体任务的job Map
     */
    private static final Map<TaskStaticTypeEnum,Class<? extends QuartzJobBean>> TASK_JOB_CLASS_MAP = new HashMap<TaskStaticTypeEnum,
            Class<? extends QuartzJobBean>>(){{
        put(TaskStaticTypeEnum.INDEX, IndexStaticJob.class);
    }};

    /***
     * 添加定时任务
     * @param dto
     */
    @Override
    public void save(CmsTaskDto dto) {
        // uuid密码
        dto.setCode(UtilsString.uuid());
        CmsTaskEntity cmsTaskEntity = CmsTaskConverter.CONVERTER.dtoToEntity(dto);
        // 存储
        cmsTaskMapper.save(cmsTaskEntity);
        // 判断状态是否启用
        if(BooleanUtils.isTrue(dto.getEnable())){
            startTask(dto);
        }
    }

    /***
     * 执行周期和执行方式方法
     * @param cmsTaskDto
     */
    public void startTask(CmsTaskDto cmsTaskDto){
        TaskExecutionTypeEnum taskExecutionType = cmsTaskDto.getTaskExecutionType();
        // 获取到cron表达式
        String cronExpression = Objects.equals(taskExecutionType, TaskExecutionTypeEnum.EXECUTION_MODE) ? cmsTaskDto.getCronExpression() : TASK_CRON_EXPRESSION.get(cmsTaskDto.getIntervalUnit()).buildCronExpress(cmsTaskDto);
        log.info("cronExpression表达式=[{}]",cronExpression);
        if(StringUtils.contains(cronExpression,"null")){
            return;
        }
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setName(cmsTaskDto.getCode());
        jobDetailFactoryBean.setJobClass(TASK_JOB_CLASS_MAP.get(cmsTaskDto.getType()));
        jobDetailFactoryBean.afterPropertiesSet();

        // 获取到触发器
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setCronExpression(cronExpression);
        cronTriggerFactoryBean.setName(cmsTaskDto.getName()+cmsTaskDto.getCode());
        try {
            cronTriggerFactoryBean.afterPropertiesSet();
            // 调度器执行任务调度
            scheduler.scheduleJob(jobDetailFactoryBean.getObject(),cronTriggerFactoryBean.getObject());
        } catch (Exception e) {
            log.error("执行定时任务失败,message=[{}]",e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    /***
     * 获取所有定时任务
     * @return
     */
    @Override
    public List<CmsTaskDto> getList() {
        return CmsTaskConverter.CONVERTER.entityToDto(cmsTaskMapper.selectAll());
    }

    /***
     * 显示首页定时任务数据
     * @param dto
     * @return
     */
    @Override
    public Page<CmsTaskDto> getPage(CmsTaskDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsTaskConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsTaskEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).
                doSelectPage(() -> cmsTaskMapper.selectBySearchProvider(of));
        return new Page<>(page.getTotal(),CmsTaskConverter.CONVERTER.entityToDto(page.getResult()));
    }

    @Override
    public CmsTaskDto getById(Integer id) {
        return null;
    }

    @Override
    public void update(CmsTaskDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }


}
