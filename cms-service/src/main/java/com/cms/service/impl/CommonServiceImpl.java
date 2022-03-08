package com.cms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsShiro;
import com.cms.service.api.CommonService;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author guardwhy
 * @date 2022/3/6 19:07
 * 获取验证码实现类
 */
@Service
@Slf4j
public class CommonServiceImpl implements CommonService {
    // 验证码常量
    private static final String IMAGE_CAPTCHA_SUFFIX = "image_captcha";

    @Autowired
    private Producer captchaProducer;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /***
     * 验证码上传业务
     */
    @Override
    public void imageCaptcha() {
        String text = captchaProducer.createText();
        // sessionId + "image_captcha"
        redisTemplate.opsForValue().set(UtilsShiro.getSession().getId() + IMAGE_CAPTCHA_SUFFIX, text, 60, TimeUnit.SECONDS);
        HttpServletResponse response = UtilsHttp.getResponse();
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 拿到验证码,关闭流
        try(ServletOutputStream outputStream = response.getOutputStream()){
            ImageIO.write(captchaProducer.createImage(text), "jpg", outputStream);
        }catch (IOException e){
            log.error("验证码生成失败");
        }
    }

    /***
     * 验证码验证
     * @param captcha
     * @return
     */
    @Override
    public String verifyImageCaptcha(String captcha) {
        // 拿到验证码
        String redisCaptcha = redisTemplate.opsForValue().get(UtilsShiro.getSession().getId() + IMAGE_CAPTCHA_SUFFIX);
        // 条件判断
        if(Objects.isNull(redisCaptcha)){
            return "验证已失效请重新输入!";
        }
        if(!StringUtils.equals(captcha, redisCaptcha)){
            return "验证码输入错误!!!!";
        }
        return null;
    }
}
