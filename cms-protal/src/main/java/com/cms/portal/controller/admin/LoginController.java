package com.cms.portal.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guardwhy
 * @date 2022/2/27 12:47
 */
@Controller
public class LoginController {
    @GetMapping("login.do")
    public String toLogin(){
        return "/admin/login";
    }
}
