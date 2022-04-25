package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.dao.enums.TaskExecutionCycleUnitEnum;
import com.cms.dao.enums.TaskExecutionTypeEnum;
import com.cms.dao.enums.TaskStaticTypeEnum;
import com.cms.service.api.CmsTaskService;
import com.cms.service.dto.CmsTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guardwhy
 * @date 2022/4/22 21:50
 * task任务控制器
 */
@Controller
@RequestMapping("task")
public class TaskController {
    // 注入定时任务业务层
    @Autowired
    private CmsTaskService cmsTaskService;
    /***
     * 跳转到task页面
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("task", "index");
    }

    /***
     * 定时任务添加页面
     * @param model
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(Model model){
        // 类型
        model.addAttribute("taskType", TaskStaticTypeEnum.values());
        // 方式
        model.addAttribute("taskExecutionType", TaskExecutionTypeEnum.values());
        // 任务执行周期单位
        model.addAttribute("taskExecutionCycle", TaskExecutionCycleUnitEnum.values());
        return UtilsTemplate.adminTemplate("task","add");
    }

    /***
     * 添加定时任务策略类
     * @param cmsTaskDto
     * @return
     */
    @PostMapping("add.do")
    @ResponseBody
    public Result doAdd(CmsTaskDto cmsTaskDto){
        cmsTaskService.save(cmsTaskDto);
        return Result.success();
    }

    /***
     * 显示首页定时任务数据
     * @param cmsTaskDto
     * @return
     */
    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsTaskDto cmsTaskDto){
        return Result.success(cmsTaskService.getPage(cmsTaskDto));
    }
}
