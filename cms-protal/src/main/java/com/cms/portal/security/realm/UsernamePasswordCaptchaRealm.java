package com.cms.portal.security.realm;

import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

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
        // 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 现在副表中查找用户是否存在
        CmsUserDto cmsUserDto = cmsUserService.selectByUsername(username);
        if(Objects.isNull(cmsUserDto)){
            throw new UnknownAccountException();
        }
        // 检验用户状态 禁用！！
        return null;
    }
}
