package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.service.api.CmsSiteService;
import com.cms.service.api.CmsStaticPageService;
import com.cms.service.dto.CmsSiteDto;
import org.apache.commons.lang3.BooleanUtils;
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
    @Autowired
    private CmsSiteService cmsSiteService;

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
        // 拿到cmsSite
        CmsSiteDto cmsSite = cmsSiteService.get();
        // 站点是否开启静态化
        Boolean staticIndex = cmsSite.getStaticIndex();
        // 条件判断
        if(BooleanUtils.isFalse(staticIndex)){
            return Result.failed("请先在站点设置中开启静态化首页");
        }
        // 调用静态页面业务层
        cmsStaticPageService.staticIndex();
        return Result.success();
    }

    /***
     * 删除静态化页面
     * @return
     */
    @PostMapping("deleteIndex.do")
    @ResponseBody
    public Result<String> doDeleteIndex(){
        boolean result = cmsStaticPageService.deleteIndex();
        // 条件判断
        if(BooleanUtils.isTrue(result)){
            return Result.success();
        }
        return Result.failed("删除失败!!!");
    }
}
