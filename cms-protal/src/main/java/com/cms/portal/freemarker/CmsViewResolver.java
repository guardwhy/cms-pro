package com.cms.portal.freemarker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author guardwhy
 * @date 2022/3/4 9:26
 * 继承视图解析类FreeMarkerView, 重写exposeHelpers方法,
 */
@Slf4j
public class CmsViewResolver extends FreeMarkerView {
    //后台路径
    private static final String ADMIN_PATH = "/admin/cms/";


    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        // getContextPath():获取项目的根路径
        String requestURI = request.getRequestURI();
        // getServletPath():获取能够与web.xml中的<servlet-mapping>标签中"ur-pattern"中匹配的路径，注意是完全匹配的部分，*的部分不包括
        String contextPath = request.getContextPath();
        // getRequestURI()返回除去host (域名或者ip)部分的路径
        String servletPath = request.getServletPath();
        // 添加页面和修改页面
        List<String> includeGoBackList = Arrays.asList("add.do", "edit.do","error.do");
        //就认为是后台请求路径
        if (requestURI.contains(ADMIN_PATH)) {
            model.put("adminPath", contextPath + servletPath);
        }
        //判断回退按钮
        includeGoBackList.forEach(x->{
            if(requestURI.contains(x)){
                model.put("goBack",true);
            }
        });
        model.put("basePath", contextPath);
    }
}
