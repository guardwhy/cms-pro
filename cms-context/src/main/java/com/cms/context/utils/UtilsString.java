package com.cms.context.utils;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

/**
 * @author guardwhy
 * @date 2022/4/23 22:06
 * 字符串操作相关工具
 */
public class UtilsString {
    /***
     * uuid基于时间
     * @return  字符串
     */
    public static String uuid(){
        UUID uuid = Generators.timeBasedGenerator().generate();
        // 返回关键字
        return uuid.toString();
    }
}
