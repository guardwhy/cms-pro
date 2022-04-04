package com.cms.portal.interceptor;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsShiro;
import com.cms.core.annotation.DoLog;
import com.cms.service.api.CmsLogService;
import com.cms.service.dto.CmsLogDto;
import com.cms.service.dto.CmsUserDto;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author guardwhy
 * @date 2022/3/21 14:43
 * 日志拦截器
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CmsLogService cmsLogService;
    // 注入线程池
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 拿到handlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取注解日志
        DoLog doLog = handlerMethod.getMethodAnnotation(DoLog.class);
        Optional.ofNullable(doLog).ifPresent(x -> {
            // 获取url
            String url = request.getRequestURI();
            // 获取ip地址
            String ip = UtilsHttp.getRemoteAddress();
            // 获取内容
            String content = doLog.content();
            // 使用线程池
            threadPoolTaskExecutor.execute(() -> {
                // 获取当前登录用户
                Subject subject = UtilsShiro.getSubject();
                CmsUserDto cmsUserDto = (CmsUserDto) subject.getPrincipal();
                cmsLogService.save(CmsLogDto.of(cmsUserDto.getId(), cmsUserDto.getUsername(), ip, url, content));
            });
        });
        super.postHandle(request, response, handler, modelAndView);
    }
}
