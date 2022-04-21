package com.cms.context.utils;

import com.cms.context.constant.ConstantsPool;
import com.cms.context.foundation.Result;
import com.cms.core.foundation.Page;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/3/6 19:47
 */
@Slf4j
public class UtilsHttp {

    private static final String HEADER_REAL_IP = "X-Real-IP";
    private static final String UNKNOWN = "unknown";
    private static final String SLASH = "../";
    private static final String BACKSLASH = "..\\";
    private static final String HEADER_FORWARDED_FOR = "X-Forwarded-For";
    private static final String COMMA = ",";
    private static final String IP_EMPTY = "0:0:0:0:0:0:0:1";
    private static final String IP_LOOP = "127.0.0.1";

    // 分页信息
    private static final int DEFAULT_SIZE = 10;
    private static final String ORDER_BY = "orderBy";
    private static final String PAGE_SIZE = "pageSize";
    private static final String PAGE_CURRENT = "pageCurrent";

    /**
     * 获取request对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取response对象
     * @return
     */
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }

    /**
     * 获取访问者IP
     * 在一般情况下使用Request.getRemoteAddress()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddress()。
     * @return string
     */
    public static String getRemoteAddress() {
        HttpServletRequest request = getRequest();
        // 拿到当前用户的ip
        String ip = request.getHeader(HEADER_REAL_IP);
        if (!StringUtils.isBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip.contains(SLASH) || ip.contains(BACKSLASH)) {
                return "";
            }
            return ip;
        }
        ip = request.getHeader(HEADER_FORWARDED_FOR);
        if (!StringUtils.isBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(COMMA);
            if (index != -1) {
                ip = ip.substring(0, index);
            }
            if (ip.contains(SLASH) || ip.contains(BACKSLASH)) {
                return "";
            }
            return ip;
        } else {
            ip = request.getRemoteAddr();
            if (ip.contains(SLASH) || ip.contains(BACKSLASH)) {
                return "";
            }
            if (ip.equals(IP_EMPTY)) {
                ip = IP_LOOP;
            }
            return ip;
        }
    }

    /***
     * 响应异常处理 会区分是ajax请求还是页面请求 进行重定向或直接返回json数据
     * @param info 异常信息
     * @param path 跳转路径
     * @return result
     */
    public static Result<String> responseExceptionHandler(String info, String path){
        HttpServletRequest request = getRequest();
        // 判断是不是ajax请求
        if(Objects.isNull(request.getHeader(ConstantsPool.HEADER_X_REQUESTED_WITH))){
            // 拿到response
            HttpServletResponse response = getResponse();
            try {
                // 跳转页面
                response.sendRedirect(request.getServletPath() + path);
            } catch (IOException e) {
                log.error("异常跳转失败！！！");
            }
        }
        return Result.failed(info);
    }

    /***
     * 获取前端请求的分页信息
     * @return
     */
    public static Page getPageInfo(){
        HttpServletRequest request = getRequest();
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageCurrent = request.getParameter(PAGE_CURRENT);
        return new Page(StringUtils.isNotBlank(pageCurrent)?Integer.parseInt(pageCurrent):1,
                StringUtils.isNotBlank(pageSize)?Integer.parseInt(pageSize):DEFAULT_SIZE,request.getParameter(ORDER_BY));
    }

    /**
     * 分页信息的内部类
     */
    public static final class Page{
        private int pageCurrent;
        private String orderBy;
        private int pageSize;

        public Page(int pageCurrent, int pageSize,String orderBy) {
            this.pageCurrent = pageCurrent;
            this.orderBy = orderBy;
            this.pageSize = pageSize;
        }

        public int getPageCurrent() {
            return pageCurrent;
        }

        public void setPageCurrent(int pageCurrent) {
            this.pageCurrent = pageCurrent;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    /**
     * 获取SpringMVC
     * @param request
     * @return
     */
    public static WebApplicationContext getWebApplicationContext(ServletRequest request){
        return  (WebApplicationContext)request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    }

}