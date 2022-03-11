package com.cms.portal.security.filter;

import com.alibaba.fastjson.JSON;
import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsShiro;
import com.cms.service.api.CommonService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/3/3 15:30
 * 判断登陆拦截
 */
public class CmsAuthenticationFilter extends FormAuthenticationFilter {
    // 设置登录前置路径
    private static final String ADMIN_LOGIN = "/admin/cms/login.do";

    @Autowired
    private CommonService commonService;


    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request) ||
                this.pathsMatch(ADMIN_LOGIN, request);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");
        String captcha = commonService.verifyImageCaptcha(WebUtils.getCleanParam(request, "captcha"));
        //TODO 跳过校验验证码
        if(1>2 && Objects.nonNull(captcha)){
            response.getWriter().write(JSON.toJSONString(Result.failed(captcha)));
            return false;
        }
        Subject subject = UtilsShiro.getSubject();
        AuthenticationToken token = this.createToken(request, response);
        try{
            subject.login(token);
            response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
        }catch(UnknownAccountException | IncorrectCredentialsException e){
            response.getWriter().write(JSON.toJSONString(Result.failed("用户名或密码错误,请重新输入!")));
        }catch (DisabledAccountException e){
            response.getWriter().write(JSON.toJSONString(Result.failed(e.getMessage())));
        }
        response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
        return false;
    }

}
