package com.cms.context.utils;

/**
 * @author guardwhy
 * @date 2022/3/15 14:18
 */
public class UtilsTemplate {
    // 构造方法
    public UtilsTemplate() {

    }

    /***
     * 后台模板方法
     * @param template
     * @return
     */
    public static String adminTemplate(String template){
        return "admin/" + template;
    }
}
