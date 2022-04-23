package com.cms.portal.controller.admin;

import com.cms.context.utils.UtilsTemplate;
import com.cms.dao.enums.TaskExecutionCycleUnitEnum;
import com.cms.dao.enums.TaskExecutionTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guardwhy
 * @date 2022/4/22 21:50
 * task任务控制器
 */
@Controller
@RequestMapping("task")
public class TaskController {
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
        model.addAttribute("taskType", TaskExecutionTypeEnum.values());
        // 方式
        model.addAttribute("taskExecutionType", TaskExecutionTypeEnum.values());
        // 单位
        model.addAttribute("taskExecutionCycle", TaskExecutionCycleUnitEnum.values());
        return UtilsTemplate.adminTemplate("task","add");
    }
}
