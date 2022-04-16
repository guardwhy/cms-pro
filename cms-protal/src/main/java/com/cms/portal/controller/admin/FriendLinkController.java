package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.dto.CmsFriendLinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author guardwhy
 * @date 2022/4/15 15:53
 * 友情链接控制器
 */
@Controller
@RequestMapping("friendLink")
public class FriendLinkController {

    // 注入链接业务层
    @Autowired
    private CmsFriendLinkService cmsFriendLinkService;

    /***
     * 展示首页index
     * @return
     */
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("friend", "index");
    }

    /***
     * 添加页面
     * @return
     */
    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("friend", "add");
    }

    /***
     * 添加友情链接功能
     * @param cmsFriendLinkDto
     * @return
     */
    @PostMapping("add.do")
    @ResponseBody
    public Result<String> doAdd(CmsFriendLinkDto cmsFriendLinkDto){
        // 业务层调用方法
        cmsFriendLinkService.save(cmsFriendLinkDto);
        return Result.success();
    }

    /***
     * 查询友情链接页面
     * @param cmsFriendLinkDto
     * @return
     */
    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsFriendLinkDto cmsFriendLinkDto){
        return Result.success(cmsFriendLinkService.getPage(cmsFriendLinkDto));
    }

    /***
     * 修改友情链接页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit.do")
    public String toEdit(Integer id, Model model){
        model.addAttribute("data", cmsFriendLinkService.getById(id));
        return UtilsTemplate.adminTemplate("friend", "edit");
    }
}
