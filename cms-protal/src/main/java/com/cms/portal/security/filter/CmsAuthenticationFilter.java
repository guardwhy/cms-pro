package com.cms.portal.security.filter;

import com.alibaba.fastjson.JSON;
import com.cms.context.foundation.Result;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author guardwhy
 * @date 2022/3/3 15:30
 */
public class CmsAuthenticationFilter extends FormAuthenticationFilter {
    // 设置登录前置路径
    private static final String ADMIN_LOGIN = "/admin/cms/login.do";

    @Override
    // 判断是前台登录或者是后台登录请求
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request) ||
                this.pathsMatch(ADMIN_LOGIN, request);
    }

    @Override
    // 响应登录JSON
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 睡眠3000s
        Thread.sleep(3000);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Result.success("登录成功!!!")));
        return false;
    }
}
