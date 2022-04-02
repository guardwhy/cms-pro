package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.service.dto.CmsRoleDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guardwhy
 * @date 2022/4/1 22:48
 */
@Controller
@RequestMapping("role")
public class RoleController {

    /***
     * 拿到角色index
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("role", "index");
    }

    @PostMapping("page.do")
    @ResponseBody
    // 请求数据
    public Result doPage(CmsRoleDto cmsRoleDto){
        return Result.success();
    }
}
