package com.cms.portal.security.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author guardwhy
 * @date 2022/3/3 15:30
 */
public class CmsAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    // 判断是前台登录或者是后台登录请求
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request) ||
                this.pathsMatch("/admin/cms/login.do", request);
    }
}
