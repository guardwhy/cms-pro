package com.cms.portal.security.realm;

import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author guardwhy
 * @date 2022/3/3 15:32
 */
public class UsernamePasswordCaptchaRealm extends AuthorizingRealm {

    // cmsUserService
    @Autowired
    private CmsUserService cmsUserService;
    @Override
    // 用户执行认证【登录】
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    // 用户执行授权【访问控制】
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        CmsUserPrimaryDto cmsUserPrimaryDto = cmsUserService.selectByUsername(username);
        return null;
    }
}
