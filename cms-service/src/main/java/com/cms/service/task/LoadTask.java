package com.cms.service.task;

import com.cms.service.api.CmsTaskService;
import com.cms.service.dto.CmsTaskDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
/**
 * @author guardwhy
 * @date 2022/4/22 21:45
 * 任务TaskLoadTask加载
 */
@Slf4j
public class LoadTask {
    // 注入cmsTaskService
    @Autowired
    private CmsTaskService cmsTaskService;

    @PostConstruct
    public void start(){
        List<CmsTaskDto> list = cmsTaskService.getList();
        list.forEach(x->{
            if(BooleanUtils.isTrue(x.getEnable())){
                cmsTaskService.startTask(x);
            }
        });
    }
}
