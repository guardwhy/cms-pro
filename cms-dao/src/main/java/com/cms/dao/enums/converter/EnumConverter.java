package com.cms.dao.enums.converter;

import com.cms.core.foundation.BaseEnum;
import com.cms.dao.enums.StaticWebSuffixEnum;
import com.cms.dao.enums.UserStatusEnum;

import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/3/11 13:28
 */
public class EnumConverter {
    /**
     * 用于将dto中枚举转换为entity中的integer类型
     * @param Enumeration
     * @param <E>
     * @return
     */
    public static <E extends BaseEnum> Integer toInteger(E Enumeration) {
        return (Enumeration != null) ? Enumeration.getOrdinal() : null;
    }


    /**
     * 用于将entity中int类型转换为UserStatusEnum枚举类型
     *
     * @param status
     * @return
     */
    public static UserStatusEnum toUserStatusEnum(int status) {
        return (UserStatusEnum) converter(UserStatusEnum.values(), status);
    }

    /**
     * 用于将entity中int类型转换为StaticWebSuffixEnum枚举类型
     *
     * @param status
     * @return
     */
    public static StaticWebSuffixEnum toStaticWebSuffixEnum(int status) {
        return (StaticWebSuffixEnum) converter(StaticWebSuffixEnum.values(), status);
    }

    /**
     * 通用枚举转换器 统一循环枚举比对
     * @param enums
     * @param status
     * @return
     */
    public static BaseEnum converter(BaseEnum[] enums, int status) {
        for (BaseEnum enumeration : enums) {
            if (Objects.equals(enumeration.getOrdinal(), status)) {
                return enumeration;
            }
        }
        return null;
    }
}
