package com.cms.core.annotation;

import java.lang.annotation.*;

/**
 * @author guardwhy
 * @date 2022/3/21 14:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoLog {
    /**
     * 日志的内容
     */
    String content();
}
