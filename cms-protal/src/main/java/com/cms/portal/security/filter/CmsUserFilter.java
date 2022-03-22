package com.cms.portal.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author guardwhy
 * @date 2022/3/3 15:31
 * 自定义User用户登录拦截器
 */

@Getter
@Setter
public class CmsUserFilter extends UserFilter {
    private String  adminLoginUrl;
    private String adminPrefix;

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse response) throws Exception {
        // 拿到HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) req;
        // 保存request
        this.saveRequest(request);
        // 获取requestURI
        String requestURI = request.getRequestURI();
        // 跳转
        WebUtils.issueRedirect(request, response, (requestURI.startsWith(adminPrefix)) ? adminLoginUrl: this.getLoginUrl());
        return false;
    }
}
