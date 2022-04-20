package com.cms.context.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @author guardwhy
 * @date 2022/4/20 11:13
 * 获取项目路径
 */
@Component
public class UtilsServletContext implements ServletContextAware {
    // 导入环境对象
    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * @param path 传入的路径
     * @return 容器路径
     */
    public String getRealPath(String path) {
        return servletContext.getRealPath(StringUtils.isBlank(path) ? "/" : path);
    }
}
