package com.cms.portal.controller.admin;

import com.cms.context.utils.UtilsTemplate;
import com.cms.service.api.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guardwhy
 * @date 2022/3/17 11:46
 */
@Controller
@RequestMapping("site")
public class SiteController {
    // 注入CmsSiteService
    @Autowired
    private CmsSiteService cmsSiteService;

    @GetMapping("index.do")
    public String toIndex(Model model){
        // 发送到前端页面
        model.addAttribute("data", cmsSiteService.get());
        return UtilsTemplate.adminTemplate("site", "index");
    }
}
