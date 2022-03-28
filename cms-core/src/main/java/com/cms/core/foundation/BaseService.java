package com.cms.core.foundation;

import java.io.Serializable;

/**
 * @author guardwhy
 * @date 2022/3/11 12:52
 */
public interface BaseService<DTO extends BaseDto<PK>,PK extends Serializable> {

    /***
     * 统一添加
     * @param dto
     */
    void save(DTO dto);
    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    DTO getById(PK id);

    /***
     * 修改
     * @param dto
     */
    void update(DTO dto);

    /***
     * 根据主键id删除
     * @param id
     */
    void deleteById(PK id);
}
