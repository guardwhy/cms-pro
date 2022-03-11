package com.cms.core.foundation;

import java.io.Serializable;

/**
 * @author guardwhy
 * @date 2022/3/11 12:52
 */
public interface BaseService<DTO extends BaseDto<PK>,PK extends Serializable> {
    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    DTO getById(PK id);
}
