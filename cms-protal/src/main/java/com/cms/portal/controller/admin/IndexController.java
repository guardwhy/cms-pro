package com.cms.portal.controller.admin;

import com.cms.context.utils.UtilsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guardwhy
 * @date 2022/3/15 14:45
 */
@Controller
public class IndexController {
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("index");
    }
}
