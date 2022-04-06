package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsRolePermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/1 22:59
 * 角色权限mapper
 */
public interface CmsRolePermissionMapper extends BaseMapper<CmsRolePermissionEntity,Integer> {
    /***
     * 批量插入
     * @param permissionList 权限结合
     * @param roleId 角色id
     */
    void batchInsert( @Param("permissionList") List<Integer> permissionList, @Param("roleId") Integer roleId);

    /***
     * 根据permissionId 删除
     * @param permissionId  权限id
     */
    void deleteByPermissionId(Integer permissionId);
}
