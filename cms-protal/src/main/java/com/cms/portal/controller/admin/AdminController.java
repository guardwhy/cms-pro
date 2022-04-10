package com.cms.portal.controller.admin;

import com.cms.context.utils.UtilsTemplate;
import com.cms.service.api.CmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    // 注入角色业务层
    @Autowired
    private CmsRoleService cmsRoleService;

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
    public String toAdd(Model model){
        // 执行到前台
        model.addAttribute("roles", cmsRoleService.getList());
        return UtilsTemplate.adminTemplate("admin", "add");
    }
}
