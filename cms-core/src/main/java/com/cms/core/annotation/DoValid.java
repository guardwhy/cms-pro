package com.cms.core.annotation;

import java.lang.annotation.*;

/**
 * @author guardwhy
 * @date 2022/3/20 17:29
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoValid {

}
