package com.cms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cms.context.utils.UtilsShiro;
import com.cms.service.api.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Objects;

import static com.cms.context.constant.ConstantsPool.IMAGE_CAPTCHA_SUFFIX;

/**
 * @author guardwhy
 * @date 2022/3/6 19:07
 * 获取验证码实现类
 */
@Service
@Slf4j
public class CommonServiceImpl implements CommonService {
    // 引入redisTemplate
    @Resource
    private RedisTemplate<String, String> redisTemplate;

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