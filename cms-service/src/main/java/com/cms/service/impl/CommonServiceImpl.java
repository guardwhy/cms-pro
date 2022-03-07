package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsShiro;
import com.cms.service.api.CommonService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author guardwhy
 * @date 2022/3/6 19:07
 * 获取验证码实现类
 */
public class CommonServiceImpl implements CommonService {
    // 验证码常量
    private static String IMAGE_CAPTCHA_SUFFIX = "image_captcha";

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void imageCaptcha() {
        String text = captchaProducer.createText();
        // sessionId + "image_captcha"
        redisTemplate.opsForValue().set(UtilsShiro.getSession().getId() + IMAGE_CAPTCHA_SUFFIX, text, 60, TimeUnit.SECONDS);
        HttpServletResponse response = UtilsHttp.getResponse();
    }
}
