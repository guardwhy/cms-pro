package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.context.utils.UtilsTree;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.api.CmsRoleService;
import com.cms.service.dto.CmsPermissionDto;
import com.cms.service.dto.CmsRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/1 22:48
 */
@Controller
@RequestMapping("role")
public class RoleController {
    // 调用cmsPermissionService
    @Autowired
    private CmsPermissionService cmsPermissionService;
    // 调用角色cmsRoleService
    @Autowired
    private CmsRoleService cmsRoleService;

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

    /***
     * 添加页面
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("role", "add");
    }

    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加角色")
    @DoValid
    public Result doAdd(@Valid CmsRoleDto cmsRoleDto, BindingResult result){
        cmsRoleService.save(cmsRoleDto);
        return Result.success();
    }

    /***
     * 返回添加角色情况
     * @return
     */
    @PostMapping("permission.do")
    @ResponseBody
    public Result doPermission(){
        List<CmsPermissionDto> permissionList =cmsPermissionService.getTree(null);
        UtilsTree.recursion(permissionList);
        return Result.success((ArrayList) permissionList);
    }
}
