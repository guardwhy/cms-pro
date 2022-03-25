package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsPermissionDto;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/3/21 20:53
 */
public interface CmsPermissionService extends BaseService<CmsPermissionDto, Integer> {
    /***
     * 查询列表
     * @param cmsPermissionDto
     * @return
     */
    List<CmsPermissionDto> getList(CmsPermissionDto cmsPermissionDto);
}
