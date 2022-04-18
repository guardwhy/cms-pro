package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.dao.enums.StaticWebSuffixEnum;
import com.cms.service.api.CmsSiteService;
import com.cms.service.dto.CmsSiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author guardwhy
 * @date 2022/3/17 11:46
 */
@Controller
@RequestMapping("site")
public class SiteController {
    // 注入CmsSiteService
    @Autowired
    private CmsSiteService cmsSiteService;

    @GetMapping("index.do")
    public String toIndex(Model model){
        // 发送到前端页面
        model.addAttribute("data", cmsSiteService.get());
        model.addAttribute("staticWebSuffix", StaticWebSuffixEnum.values());
        return UtilsTemplate.adminTemplate("site", "index");
    }

    @PostMapping("edit.do")
    @ResponseBody
    @DoValid
    @DoLog(content = "修改站点配置")
    public Result<String> doEdit(@Valid CmsSiteDto cmsSiteDto, BindingResult result){
        /**
         * 调用业务层
         */
        cmsSiteService.update(cmsSiteDto);
        return Result.success();
    }
}
