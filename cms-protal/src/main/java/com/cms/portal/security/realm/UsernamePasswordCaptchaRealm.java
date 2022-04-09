package com.cms.portal.security.realm;

import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/3/3 15:32
 */
public class UsernamePasswordCaptchaRealm extends AuthorizingRealm {

    @Autowired
    private CmsUserService cmsUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //现在副表中查找用户是否存在
        CmsUserDto cmsUserDto = cmsUserService.selectByUsername(username);
        if(Objects.isNull(cmsUserDto)){
            throw new UnknownAccountException();
        }
        //校验用户状态 禁用
        verifyStatus(cmsUserDto.getStatus());
        //查询用户主表信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(cmsUserDto, cmsUserDto.getPassword(),
                ByteSource.Util.bytes(cmsUserDto.getSalt()), getName());
        // 清理认证信息
        super.clearCachedAuthenticationInfo(simpleAuthenticationInfo.getPrincipals());
        // 返回密码校验
        return simpleAuthenticationInfo;
    }

    /**
     * 校验状态
     * @param userStatus
     */
    private void verifyStatus(Boolean userStatus){
        if(BooleanUtils.isFalse(userStatus)){
            throw new DisabledAccountException("该账号已被禁用,请联系管理员!");
        }
    }
}
