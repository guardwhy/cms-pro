package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.dao.enums.PermissionTypeEnum;
import com.cms.service.dto.CmsPermissionDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String toAdd(Model model){
        // 获取权限类型枚举值
        PermissionTypeEnum[] values = PermissionTypeEnum.values();
        // 自动实例化一个Model对象用于向视图中传值
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission", "add");
    }

    @PostMapping("add.do")
    public Result<String> doAdd(CmsPermissionDto cmsPermissionDto){
        // 返回成功
        return Result.success();
    }
}
