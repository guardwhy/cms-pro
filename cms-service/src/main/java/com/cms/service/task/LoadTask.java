package com.cms.service.task;

import com.cms.service.api.CmsTaskService;
import com.cms.service.dto.CmsTaskDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
public class LoadTask {

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
