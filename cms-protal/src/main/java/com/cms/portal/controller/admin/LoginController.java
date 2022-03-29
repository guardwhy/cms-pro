package com.cms.portal.controller.admin;

import com.cms.context.utils.UtilsShiro;
import com.cms.context.utils.UtilsTemplate;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.cms.context.constant.ConstantsPool.IMAGE_CAPTCHA_SUFFIX;

/**
 * @author guardwhy
 * @date 2022/2/27 12:47
 */
@Controller
@Slf4j
public class LoginController {
    // 注入Producer
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private Producer captchaProducer;

    /***
     * 登录操作
     * @return
     */
    @GetMapping("login.do")
    public String toLogin(){
        // 拿到subject
        Subject subject = UtilsShiro.getSubject();
        // 条件判断
        if(subject.isAuthenticated()){
            // 登入成功，跳转
            return "redirect:index.do";
        }
        // 否则跳到登入页面
        return "/admin/login";
    }

    /***
     * 拿到验证码
     */
    @GetMapping("captcha.do")
    public void doCaptcha( HttpServletResponse response){
        String text = captchaProducer.createText();
        // sessionId + "image_captcha"
        redisTemplate.opsForValue().set(UtilsShiro.getSession().getId() + IMAGE_CAPTCHA_SUFFIX, text, 60, TimeUnit.SECONDS);
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 拿到验证码,关闭流response
        try(ServletOutputStream outputStream = response.getOutputStream()){
            ImageIO.write(captchaProducer.createImage(text), "jpg", outputStream);
        }catch (IOException e){
            log.error("验证码生成失败");
        }
    }

    /***
     * 异常操作
     * @return
     */
    @GetMapping("error.do")
    public String toError(){
        return UtilsTemplate.adminTemplate("error");
    }
}
