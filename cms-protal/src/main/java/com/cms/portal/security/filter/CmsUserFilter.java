package com.cms.portal.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.web.filter.authc.UserFilter;

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
}
