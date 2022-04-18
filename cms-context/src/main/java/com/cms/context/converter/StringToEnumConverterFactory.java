package com.cms.context.converter;

import com.cms.core.foundation.BaseEnum;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;
import java.util.WeakHashMap;

/**
 * @author guardwhy
 * @date 2022/4/18 15:09
 * 枚举缓存
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    // 软引用
    private static final WeakHashMap<String, Converter> CACHE_MAP = new WeakHashMap<>();

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        // 拿到名称
        String simpleName = targetType.getSimpleName();
        // 拿到simpleName
        Converter converter = CACHE_MAP.get(simpleName);
        if(Objects.isNull(converter)){
            converter = new StringToEnumConverter(targetType);
            // 添加
            CACHE_MAP.put(simpleName, converter);
        }
        return converter;
    }

    public class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {

        private Class<T> clazz;

        public StringToEnumConverter(Class<T> enumType) {
            clazz = enumType;
        }
        /***
         * 获取枚举
         * @param str
         * @return
         */
        @Override
        public T convert(String str) {
            for (T enumType : clazz.getEnumConstants()) {
                if (Objects.equals(enumType.getOrdinal(), Integer.parseInt(str))) {
                    return enumType;
                }
            }
            return null;
        }
    }
}

