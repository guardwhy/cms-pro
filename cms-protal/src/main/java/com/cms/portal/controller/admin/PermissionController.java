package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.dao.enums.PermissionTypeEnum;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.dto.CmsPermissionDto;
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
import java.util.*;

/**
 * @author guardwhy
 * @date 2022/3/21 21:13
 */
@Controller
@Validated
@RequestMapping("permission")
public class PermissionController {
    // 注入权限表cmsPermissionService
    @Autowired
    private CmsPermissionService cmsPermissionService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("permission", "index");
    }

    @GetMapping("add.do")
    public String toAdd(Model model,Integer parentId){
        // 条件判断
        if(Objects.nonNull(parentId)){
            model.addAttribute("parentId", parentId);
        }
        // 自动实例化一个Model对象用于向视图中传值
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission", "add");
    }

    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加权限")
    @DoValid
    public Result<String> doAdd(CmsPermissionDto cmsPermissionDto, BindingResult result){
        cmsPermissionService.save(cmsPermissionDto);
        // 返回成功
        return Result.success();
    }

    /***
     * 修改时显示树形菜单数据
     * @return
     */
    @PostMapping("selectTree.do")
    @ResponseBody
    public Result doSelectTree(Integer excludeId){
        return Result.success((ArrayList) cmsPermissionService.getTree(excludeId));
    }

    /***
     * 首页权限数据
     * @param cmsPermissionDto
     * @return
     */
    @GetMapping("list.do")
    @ResponseBody
    public Result doList(CmsPermissionDto cmsPermissionDto){
        return Result.success((ArrayList)cmsPermissionService.getList(cmsPermissionDto));
    }

    /***
     * 根据id删除用户权限
     * @param id
     * @return
     */
    @PostMapping("delete.do")
    @ResponseBody
    public Result doDelete(@NotNull (message = "请传递id") Integer id){
        // 根据id删除
        cmsPermissionService.deleteById(id);
        return Result.success();
    }

    /***
     * 修改权限页面数据回显
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "请输入id")Integer id, Model model){
        model.addAttribute("data", cmsPermissionService.getById(id));
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission", "edit");
    }

    /***
     * 修改权限
     * @param cmsPermissionDto
     * @param result
     * @return
     */
    @PostMapping("edit.do")
    @ResponseBody
    @DoLog(content = "修改权限")
    @DoValid
    public Result<String> doEdit(@Valid CmsPermissionDto cmsPermissionDto, BindingResult result){
        cmsPermissionService.update(cmsPermissionDto);
        return Result.success();
    }
}
