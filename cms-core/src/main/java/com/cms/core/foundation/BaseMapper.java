package com.cms.core.foundation;

import java.io.Serializable;

/**
 * @author guardwhy
 * @date 2022/3/11 12:49
 */
public interface BaseMapper<ENTITY extends BaseEntity<PK>,PK extends Serializable> {

    /***
     * 添加操作
     * @param entity
     */
    void save(ENTITY entity);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    ENTITY selectById(PK id);

    /***
     * 修改
     * @param entity
     */
    void update(ENTITY entity);
}
