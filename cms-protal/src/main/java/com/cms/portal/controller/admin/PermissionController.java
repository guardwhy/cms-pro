package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.service.dto.CmsPermissionDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guardwhy
 * @date 2022/3/21 21:13
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("permission", "index");
    }

    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("permission", "add");
    }

    @PostMapping("add.do")
    public Result<String> doAdd(CmsPermissionDto cmsPermissionDto){
        // 返回成功
        return Result.success();
    }
}
