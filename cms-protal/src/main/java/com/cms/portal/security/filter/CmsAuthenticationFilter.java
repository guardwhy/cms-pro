package com.cms.portal.security.filter;

import com.alibaba.fastjson.JSON;
import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsShiro;
import com.cms.service.api.CmsLogService;
import com.cms.service.api.CmsUserService;
import com.cms.service.api.CommonService;
import com.cms.service.dto.CmsLogDto;
import com.cms.service.dto.CmsUserDto;
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
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/3/3 15:30
 * 判断登陆拦截
 */
public class CmsAuthenticationFilter extends FormAuthenticationFilter {
    // 设置登录前置路径
    private static final String ADMIN_LOGIN = "/admin/cms/login.do";

    // 注入commonService
    @Autowired
    private CommonService commonService;

    @Autowired
    private CmsLogService cmsLogService;

    @Autowired
    private CmsUserService cmsUserService;

    @Override
    // 判断是前台登录或者是后台登录请求
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request) ||
                this.pathsMatch(ADMIN_LOGIN, request);
    }

    @Override
    // 响应登录JSON
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");
        // 拿到验证码
        String captcha =  commonService.verifyImageCaptcha(WebUtils.getCleanParam(request, "captcha"));

        // 二次登陆，跳过验证码的检验
        if(1>2 && Objects.nonNull(captcha)){
            response.getWriter().write(JSON.toJSONString(Result.failed(captcha)));
            return false;
        }
        // 拿到subject
        Subject subject = UtilsShiro.getSubject();
        // 拿到token值
        AuthenticationToken token = this.createToken(request, response);

        try{
            // 执行登录操作
            subject.login(token);
            // 调用登录成功方法
            onLoginSuccess(token, subject, request, response);
            response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
        }catch(UnknownAccountException | IncorrectCredentialsException e){
            response.getWriter().write(JSON.toJSONString(Result.failed("用户名或密码错误,请重新输入!")));
        }catch (DisabledAccountException e){
            response.getWriter().write(JSON.toJSONString(Result.failed(e.getMessage())));
        } catch (Exception e){
            // 用户有可能已经登录,其他错误
            if(subject.isAuthenticated()){
                response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
            }else {
                response.getWriter().write(JSON.toJSONString(Result.failed("网络连接失败,请重新登录")));
            }
        }
        return false;
    }

    // 登录操作
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 获取用户的请求路径
        String url = httpServletRequest.getRequestURI();
        // 获取IP地址
        String ip = UtilsHttp.getRemoteAddress();
        CmsUserDto cmsUserDto = (CmsUserDto) subject.getPrincipal();
        // 设置IP和Session
        cmsUserDto.setLastLoginIp(ip);
        cmsUserDto.setSessionId(UtilsShiro.getSession().getId());
        // 更新操作
        cmsUserService.update(cmsUserDto);
        // 保存操作
        cmsLogService.save(CmsLogDto.of(cmsUserDto.getId(), cmsUserDto.getUsername(), ip, url, "用户后台系统登录！！！"));
        return false;
    }
}
