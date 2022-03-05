package com.cms.portal.controller.admin;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author guardwhy
 * @date 2022/2/27 12:47
 */
@Controller
@Slf4j
public class LoginController {
    // 注入Producer
    @Autowired
    private Producer captchaProducer;

    @GetMapping("login.do")
    // 登录页面
    public String toLogin(){
        return "/admin/login";
    }

    /***
     * 拿到验证码
     * @param httpServletResponse
     */
    @GetMapping("captcha.do")
    public void doCaptcha(HttpServletResponse httpServletResponse){
        String text = captchaProducer.createText();
        // 拿到图像
        BufferedImage image = captchaProducer.createImage(text);
        try(ServletOutputStream outputStream = httpServletResponse.getOutputStream()){
            ImageIO.write(image, "jpg", outputStream);
        }catch (IOException e){
            log.error("验证码生成失败");
        }
    }
}
