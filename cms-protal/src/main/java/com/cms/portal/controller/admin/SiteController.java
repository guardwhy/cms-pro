package com.cms.portal.controller.admin;

import com.cms.context.utils.UtilsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guardwhy
 * @date 2022/3/17 11:46
 */
@Controller
@RequestMapping("site")
public class SiteController {

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("site", "index");
    }
}
