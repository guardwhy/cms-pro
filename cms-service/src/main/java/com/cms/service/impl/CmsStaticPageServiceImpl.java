package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsServletContext;
import com.cms.core.exception.BusinessException;
import com.cms.dao.enums.StaticWebSuffixEnum;
import com.cms.service.api.CmsSiteService;
import com.cms.service.api.CmsStaticPageService;
import com.cms.service.dto.CmsSiteDto;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author guardwhy
 * @date 2022/4/18 21:44
 * 静态化业务层接口实现类
 */
@Slf4j
@Service
public class CmsStaticPageServiceImpl implements CmsStaticPageService {
    // 注入站点业务接口
    @Autowired
    private CmsSiteService cmsSiteService;
    // 注入获取项目路径
    @Autowired
    private UtilsServletContext utilsServletContext;
    // 注入freeMarkerConfig;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Override
    public void staticIndex() {
        // 拿到站点
        CmsSiteDto cmsSite = cmsSiteService.get();
        // 站点是否开启首页静态化
        Boolean staticIndex = cmsSite.getStaticIndex();
        // 条件判断
        if(BooleanUtils.isFalse(staticIndex)){
            return;
        }

        // 静态化存放目录
        String staticDir = cmsSite.getStaticDir();
        if(StringUtils.isEmpty(staticDir)){
            throw new BusinessException("请先在站点设置中填写静态页目录");
        }

        // 首页模板路径
        String templatePath = cmsSite.getTplIndex();
        //输出路径
        String outPutPath = staticDir +"/index"+StaticWebSuffixEnum.HTML.getLabel();
        // 获取基于项目的完整路径
        String realOutPutPath = utilsServletContext.getRealPath(outPutPath);
        log.info("realOutPutPath=[{}]",realOutPutPath);
        File realOutPutPathFile = new File(realOutPutPath);
        File parentDir = realOutPutPathFile.getParentFile();
        // 条件判断
        if(!parentDir.exists()){
            parentDir.mkdirs();
        }
        // 拿到数据
        HashMap<String, Object> data = Maps.newHashMap();
        // 添加到暂存信息
        data.put("site", cmsSite);
        // 拿到虚拟路径
        data.put("basePath", utilsServletContext.getContextPath());
        // 将文件转换成流并且输出出去
        try(Writer writer = new OutputStreamWriter(new FileOutputStream(realOutPutPathFile), StandardCharsets.UTF_8)){
            // 获取到配置文件
            Configuration configuration = freeMarkerConfig.getConfiguration();
            // 设置编码
            configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
            // 获取首页模板路径
            Template template = configuration.getTemplate(templatePath);
            template.process(data,writer);
        }catch (Exception e){
            log.error("staticIndex生成首页模板失败=[{}]",e.getMessage());
            throw new BusinessException("生成首页模板失败！！！");
        }
    }

    /***
     * 删除静态页面
     * @return
     */
    @Override
    public boolean deleteIndex() {
        // 拿到站点
        CmsSiteDto cmsSite = cmsSiteService.get();
        // 拿到静态目录
        String staticDir = cmsSite.getStaticDir();
        HttpServletRequest request = UtilsHttp.getRequest();
        // 拿到虚拟化路径
        String contextPath = request.getContextPath();
        File file = new File(utilsServletContext.getRealPath(contextPath + "/" + staticDir + "/index" +
                StaticWebSuffixEnum.HTML.getLabel()));
        return file.delete();
    }
}
