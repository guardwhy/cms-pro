package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsPermissionEntity;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/3/21 20:58
 */
public interface CmsPermissionMapper extends BaseMapper<CmsPermissionEntity, Integer> {
    /***
     * 根据父类id查询
     * @param parentId
     * @return
     */
    List<CmsPermissionEntity> selectByParentId(Integer parentId);
}
