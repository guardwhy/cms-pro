package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsFriendLinkDto;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/15 15:46
 * 友情链接业务层接口
 */
public interface CmsFriendLinkService extends BaseService<CmsFriendLinkDto, Integer> {
    /***
     * 获取友情链接列表数据
     * @param cmsFriendLinkDto
     * @return
     */
    List<CmsFriendLinkDto> getList(CmsFriendLinkDto cmsFriendLinkDto);
}
