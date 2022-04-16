package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsFriendLinkEntity;
import com.cms.dao.mapper.CmsFriendLinkMapper;
import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.converter.CmsFriendLinkConverter;
import com.cms.service.dto.CmsFriendLinkDto;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guardwhy
 * @date 2022/4/15 15:48
 * 友情链接业务实现类
 */
@Service
public class CmsFriendLinkServiceImpl implements CmsFriendLinkService {
    // 注入友情链接mapper
    @Autowired
    private CmsFriendLinkMapper cmsFriendLinkMapper;

    @Override
    public void save(CmsFriendLinkDto dto) {
        cmsFriendLinkMapper.save(CmsFriendLinkConverter.CONVERTER.dtoToEntity(dto));
    }

    /***
     * 根据id值修改dto的数据
     * @param id
     * @return
     */
    @Override
    public CmsFriendLinkDto getById(Integer id) {
        return CmsFriendLinkConverter.CONVERTER.entityToDto(cmsFriendLinkMapper.selectById(id));
    }

    /***
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public Page<CmsFriendLinkDto> getPage(CmsFriendLinkDto dto) {
        // 拿到pageInfo
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsFriendLinkConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsFriendLinkEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(),
                pageInfo.getPageSize()).
                doSelectPage(() -> cmsFriendLinkMapper.selectBySearchProvider(of));
        return new Page<>(page.getTotal(),CmsFriendLinkConverter.CONVERTER.entityToDto(page.getResult()));
    }

    @Override
    public void update(CmsFriendLinkDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
