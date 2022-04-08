package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.context.utils.UtilsTree;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.api.CmsRolePermissionService;
import com.cms.service.api.CmsRoleService;
import com.cms.service.dto.CmsPermissionDto;
import com.cms.service.dto.CmsRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/4/1 22:48
 */
@Controller
@RequestMapping("role")
@Validated
public class RoleController {
    // 调用cmsPermissionService
    @Autowired
    private CmsPermissionService cmsPermissionService;
    // 调用角色cmsRoleService
    @Autowired
    private CmsRoleService cmsRoleService;
    // 调用角色权限cmsRolePermissionService
    @Autowired
    private CmsRolePermissionService cmsRolePermissionService;

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
        return Result.success(cmsRoleService.getPage(cmsRoleDto));
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
    public Result doPermission(Integer roleId){
        List<CmsPermissionDto> permissionList =cmsPermissionService.getTree(null);
        List<Integer> permissionIds = null;
        // 条件判断
        if(Objects.nonNull(roleId)){
            permissionIds = cmsRolePermissionService.getPermissionIdByRoleId(roleId);
        }
        UtilsTree.recursion(permissionList, permissionIds);
        return Result.success((ArrayList) permissionList);
    }

    /***
     * 修改角色
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "请输入id") Integer id, Model model){
        // 返回置前端页面
        model.addAttribute("data", cmsRoleService.getById(id));
        return UtilsTemplate.adminTemplate("role", "edit");
    }

    /***
     * 修改角色权限
     * @param cmsRoleDto
     * @param result
     * @return
     */
    @PostMapping("edit.do")
    @ResponseBody
    @DoValid
    @DoLog(content = "修改角色")
    public Result<String> doEdit(@Valid CmsRoleDto cmsRoleDto, BindingResult result){
        cmsRoleService.update(cmsRoleDto);
        return Result.success();
    }

}
