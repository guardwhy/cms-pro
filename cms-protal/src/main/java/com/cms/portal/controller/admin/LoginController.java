package com.cms.portal.controller.admin;

import com.cms.service.api.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guardwhy
 * @date 2022/2/27 12:47
 */
@Controller
@Slf4j
public class LoginController {
    // 注入Producer
    @Autowired
    private CommonService commonService;

    /***
     * 登录操作
     * @return
     */
    @GetMapping("login.do")
    public String toLogin(){
        return "/admin/login";
    }

    /***
     * 拿到验证码
     */
    @GetMapping("captcha.do")
    public void doCaptcha(){
        commonService.imageCaptcha();
    }
}
