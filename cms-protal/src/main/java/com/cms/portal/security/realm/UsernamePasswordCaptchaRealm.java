package com.cms.portal.security.realm;

import com.cms.dao.enums.UserStatusEnum;
import com.cms.service.api.CmsUserPrimaryService;
import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.apache.shiro.authc.*;
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

    @Autowired
    private CmsUserPrimaryService cmsUserPrimaryService;

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
        verifyStatus(cmsUserDto.getStatus());

        //查询用户主表信息
        CmsUserPrimaryDto cmsUserPrimaryDto = cmsUserPrimaryService.getById(cmsUserDto.getId());

        // 查询用户主表的信息
        return null;
    }

    /***
     * 检验状态
     * @param userStatusEnum
     */
    private void verifyStatus(UserStatusEnum userStatusEnum){
        if(UserStatusEnum.DISABLED.equals(userStatusEnum)){
            throw new DisabledAccountException("该账户已经被禁用,请联系管理员！！");
        }
        if(UserStatusEnum.LOCKED.equals(userStatusEnum)){
            throw new DisabledAccountException("该账号已被锁定，请联系管理员!!!");
        }
        if(UserStatusEnum.UNACTIVATED.equals(userStatusEnum)){
            throw new DisabledAccountException("该账号未被激活,请联系管理员!!!");
        }
    }
}
