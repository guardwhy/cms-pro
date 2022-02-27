package com.cms.portal.controller.admin;

import com.cms.service.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guardwhy
 * @date 2022/2/26 22:28
 */
@Controller
public class TestController {
    // 注入service
    @Autowired
   private TestService testService;

   // 注入redisTemplate
   @Autowired
   private RedisTemplate<String,String> redisTemplate;

    @GetMapping("test")
    public void test(){
        redisTemplate.opsForValue().set("abc","james");
    }
}
