package com.cms.core.foundation;

import java.io.Serializable;

/**
 * @author guardwhy
 * @date 2022/3/11 12:49
 */
public interface BaseMapper<ENTITY extends BaseEntity<PK>,PK extends Serializable> {
    /**
     * 根据id查找
     * @param id
     * @return
     */
    ENTITY selectById(PK id);
}
