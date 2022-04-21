package com.cms.portal.controller.front;

import com.cms.context.utils.UtilsServletContext;
import com.cms.context.utils.UtilsTemplate;
import com.cms.dao.enums.StaticWebSuffixEnum;
import com.cms.service.api.CmsSiteService;
import com.cms.service.dto.CmsSiteDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author guardwhy
 * @date 2022/3/15 9:43
 */
@Slf4j
@Controller
public class DynamicPageController {
    // 注入站点业务层
    @Autowired
    private CmsSiteService cmsSiteService;
    // 静态页面控制
    @Autowired
    private UtilsServletContext utilsServletContext;

    /***
     * 获取静态化页面
     * @return
     */
    @GetMapping("index.shtml")
    public String index(HttpServletRequest request, HttpServletResponse response){
        // 获取站点的信息
        CmsSiteDto cmsSite = cmsSiteService.get();
        if(BooleanUtils.isTrue(existStaticIndexPage(cmsSite))){
            // 获取路径
            String contextPath = request.getContextPath();
            try {
                // 跳转到路径
                response.sendRedirect(contextPath+"/"+cmsSite.getStaticDir()+"/index"+
                        StaticWebSuffixEnum.HTML.getLabel());
            } catch (IOException e) {
                log.error("跳转静态首页报错,错误信息=[{}]",e.getMessage());
            }
        }
        return UtilsTemplate.frontTemplate("index");
    }

    /***
     * 判断静态页面是否存在
     * @param cmsSite
     * @return
     */
    private boolean existStaticIndexPage(CmsSiteDto cmsSite){
        // 拿到静态页面
        Boolean staticIndex = cmsSite.getStaticIndex();
        log.info("判断是否开启首页静态化=【{}】", staticIndex);
        // 判断静态页面是否存在
        if(BooleanUtils.isTrue(staticIndex)){
            // 拿到静态目录
            String staticDir = cmsSite.getStaticDir();
            // 拿到文件位置，判断状态
            return BooleanUtils.isTrue(FileUtil.canReadFile(new File(utilsServletContext.getRealPath(staticDir+"/index"+
                    StaticWebSuffixEnum.HTML.getLabel()))));
        }
        // 寻找失败
        return false;
    }
}
