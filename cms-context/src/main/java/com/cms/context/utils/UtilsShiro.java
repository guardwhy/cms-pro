package com.cms.context.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author guardwhy
 * @date 2022/3/6 19:47
 */

public class UtilsShiro {

    /**
     * 通过shiro获取session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取shiro的subject
     * @return
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 生成salt值
     * @return
     */
    public static String generateSalt(){
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    /**
     * sga256散列加密
     * @param message           要加密的信息
     * @param salt              盐值
     * @param hashIterations    散列次数
     * @return
     */
    public static String sha256(String message,String salt,int hashIterations){
        return new SimpleHash("SHA-256",message,salt,hashIterations).toHex();
    }
}
