package com.cms.portal.controller.admin;

import com.cms.context.utils.UtilsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guardwhy
 * @date 2022/4/10 8:37
 * 用户控制器
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    /***
     * 管理员的首页显示
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("admin", "index");
    }

    /***
     * 显示管理员添加页面
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("admin", "add");
    }
}
