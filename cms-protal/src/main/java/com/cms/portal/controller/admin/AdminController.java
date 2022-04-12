package com.cms.portal.controller.admin;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.service.api.CmsRoleService;
import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/4/10 8:37
 * 用户控制器
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    // 注入角色业务层
    @Autowired
    private CmsRoleService cmsRoleService;
    @Autowired
    private CmsUserService cmsUserService;

    /***
     * 管理员的首页显示
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("admin", "index");
    }

    /***
     * 显示管理员添加页面
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(Model model){
        // 执行到前台
        model.addAttribute("roles", cmsRoleService.getList());
        return UtilsTemplate.adminTemplate("admin", "add");
    }

    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加管理员")
    public Result<String> doAdd(CmsUserDto cmsUserDto){
        CmsUserDto cmsUserByUsername = cmsUserService.selectByUsername(cmsUserDto.getUsername());
        if(Objects.nonNull(cmsUserByUsername)){
            return Result.failed("当前用户已经存在");
        }
        CmsUserDto cmsUserByEmail = cmsUserService.selectByEmail(cmsUserDto.getEmail());
        if(Objects.nonNull(cmsUserByEmail)){
            return Result.failed("当前邮箱已经存在");
        }
        cmsUserService.save(cmsUserDto);
        return Result.success();
    }

    /***
     * 分页
     * @param cmsUserDto
     * @return
     */
    @FastJsonView(exclude={
            @FastJsonFilter(clazz=CmsUserDto.class,props={"password","salt"})
    })
    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsUserDto cmsUserDto){
        return Result.success(cmsUserService.getPage(cmsUserDto));
    }

    /***
     * 修改管理员
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit.do")
    public String toEdit(Integer id, Model model){
        model.addAttribute("data", cmsUserService.getById(id));
        model.addAttribute("roles",cmsRoleService.getList());
        // 返回给前端页面
        return UtilsTemplate.adminTemplate("admin", "edit");
    }
}
