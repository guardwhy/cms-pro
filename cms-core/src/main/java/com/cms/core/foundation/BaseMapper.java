package com.cms.core.foundation;

import java.io.Serializable;
import java.util.List;

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

    /***
     * 修改
     * @param entity
     */
    void update(ENTITY entity);

    /***
     * 查询所有
     * @return
     */
     List<ENTITY> selectAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    ENTITY selectById(PK id);

    /***
     * 根据id进行删除
     * @param id
     */
    void deleteById(PK id);

    /***
     * 分页查询
     * @param searchProvider
     * @return
     */
    List<ENTITY> selectBySearchProvider(SearchProvider searchProvider);
}
