package com.cms.portal.controller.admin;

import com.cms.context.utils.UtilsTemplate;
import org.springframework.stereotype.Controller;
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
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("task", "index");
    }
}
