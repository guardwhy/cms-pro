package com.cms.service.impl;

import com.cms.core.exception.BusinessException;
import com.cms.dao.entity.CmsPermissionEntity;
import com.cms.dao.mapper.CmsPermissionMapper;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.converter.CmsPermissionConverter;
import com.cms.service.dto.CmsPermissionDto;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guardwhy
 * @date 2022/3/21 20:56
 */
@Service
public class CmsPermissionServiceImpl implements CmsPermissionService {

    @Autowired
    // 注入权限cmsPermissionMapper
    private CmsPermissionMapper cmsPermissionMapper;

    /***
     * 添加操作
     * @param dto
     */
    @Override
    public void save(CmsPermissionDto dto) {
        cmsPermissionMapper.save(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    /***
     * 根据id查询结果
     * @param id
     * @return
     */
    @Override
    public CmsPermissionDto getById(Integer id) {
        return CmsPermissionConverter.CONVERTER.entityToDto(cmsPermissionMapper.selectById(id));
    }

    @Override
    public void update(CmsPermissionDto dto) {

    }

    /***
     * 查询所有的权限表信息
     * @param cmsPermissionDto
     * @return
     */
    @Override
    public List<CmsPermissionDto> getList(CmsPermissionDto cmsPermissionDto) {
        return CmsPermissionConverter.CONVERTER.entityToDto(cmsPermissionMapper.selectAll());
    }

    /***
     * 根据主键id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        // 通过id拿到cmsPermissionMapper
        List<CmsPermissionEntity> cmsPermissionEntities = cmsPermissionMapper.selectByParentId(id);
        // 条件判断
        if(!CollectionUtils.isEmpty(cmsPermissionEntities)){
            throw new BusinessException("不能删除带有子类的权限");
        }
        // 删除id
        cmsPermissionMapper.deleteById(id);
    }
}
