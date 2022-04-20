package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.service.api.CmsStaticPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guardwhy
 * @date 2022/4/18 21:44
 * 静态化页面控制器
 */
@Controller
@RequestMapping("static")
public class StaticPageController {
    // 注入静态页面业务层
    @Autowired
    private CmsStaticPageService cmsStaticPageService;

    /***
     * 跳转到静态页面
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("static", "index");
    }

    /***
     * 生成首页
     * @return
     */
    @PostMapping("index.do")
    @ResponseBody
    public Result<String> doIndexStatic(){
        cmsStaticPageService.staticIndex();
        return Result.success();
    }
}
