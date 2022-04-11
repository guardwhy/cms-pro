package com.cms.portal.controller.admin;

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
 * 个人微信: 15254124776
 * 个人qq:  2301887641
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CmsRoleService cmsRoleService;
    @Autowired
    private CmsUserService cmsUserService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("admin","index");
    }

    @GetMapping("add.do")
    public String toAdd(Model model){
        model.addAttribute("roles",cmsRoleService.getList());
        return UtilsTemplate.adminTemplate("admin","add");
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

    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsUserDto cmsUserDto){
        return Result.success(cmsUserService.getPage(cmsUserDto));
    }

}
