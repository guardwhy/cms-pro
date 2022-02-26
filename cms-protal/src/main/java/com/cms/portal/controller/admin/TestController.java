package com.cms.portal.controller.admin;

import com.cms.service.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("test")
    public void test(){
        int count = testService.count();
        System.out.println(count);
    }
}
