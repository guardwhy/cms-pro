package com.cms.portal.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guardwhy
 * @date 2022/3/15 9:43
 */
@Controller
public class DynamicPageController {
    @GetMapping("index.shtml")
    public String index(){
        return "/front/default/index";
    }
}
