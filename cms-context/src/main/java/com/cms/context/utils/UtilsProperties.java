package com.cms.context.utils;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author guardwhy
 * @date 2022/4/11 22:00
 */
@Component
public class UtilsProperties implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.stringValueResolver = stringValueResolver;
    }

    /**
     * 获取属性值
     * @param key   属性
     * @return
     */
    public String getPropertiesValue(String key){
        return stringValueResolver.resolveStringValue("${"+key+"}");
    }
}
