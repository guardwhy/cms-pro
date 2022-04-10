package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsRoleDto;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/1 21:54
 * 角色接口
 */
public interface CmsRoleService extends BaseService<CmsRoleDto, Integer> {

    /***
     * 获取角色列表
     * @return
     */
    List<CmsRoleDto> getList();
}
