package com.cms.context.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @author guardwhy
 * @date 2022/3/6 19:47
 */
public class UtilsShiro {
    /***
     * 通过shiro获取session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }
}
