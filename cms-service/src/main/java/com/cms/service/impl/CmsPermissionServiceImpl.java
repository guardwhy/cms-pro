package com.cms.service.impl;

import com.cms.core.exception.BusinessException;
import com.cms.core.foundation.Page;
import com.cms.dao.entity.CmsPermissionEntity;
import com.cms.dao.mapper.CmsPermissionMapper;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.converter.CmsPermissionConverter;
import com.cms.service.dto.CmsPermissionDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/3/21 20:56
 */
@Service
public class CmsPermissionServiceImpl implements CmsPermissionService {
    // 注入权限cmsPermissionMapper
    @Autowired
    private CmsPermissionMapper cmsPermissionMapper;
    // 注入权限cmsRolePermissionMapper
    @Autowired
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    /***
     * 添加操作
     * @param dto
     */
    @Override
    public void save(CmsPermissionDto dto) {
        cmsPermissionMapper.save(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    /***
     * 根据主键id删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        // 通过id拿到cmsPermissionMapper
        List<CmsPermissionEntity> cmsPermissionEntities = cmsPermissionMapper.selectByParentId(id);
        // 条件判断
        if(!CollectionUtils.isEmpty(cmsPermissionEntities)){
            throw new BusinessException("不能删除带有子类的权限");
        }
        // 删除id
        cmsRolePermissionMapper.deleteByPermissionId(id);
        cmsPermissionMapper.deleteById(id);
    }

    @Override
    public Page<CmsPermissionDto> getPage(CmsPermissionDto dto) {
        return null;
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
     * 根据id修改权限表
     * @param dto
     */
    @Override
    public void update(CmsPermissionDto dto) {
        cmsPermissionMapper.update(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public List<CmsPermissionDto> getTree(Integer excludeId) {
        List<CmsPermissionDto> cmsPermissionDtos = getList(null);
        //存放所有数据 方便存取
        Map<Integer, CmsPermissionDto> permissionMap = Maps.newHashMap();
        //只存放parentId = 0的数据
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        //循环数据 进行处理
        cmsPermissionDtos.forEach(x -> {
            Integer id = x.getId();
            //如果当前id 等于 排除的id跳过
            if (Objects.nonNull(excludeId) && id.compareTo(excludeId) == 0) {
                return;
            }
            permissionMap.put(id, x);
            //获取当前dto的父类id
            Integer parentId = x.getParentId();
            //判断是否是顶级菜单
            if (parentId == 0) {
                permissionList.add(x);
            } else {
                CmsPermissionDto cmsPermissionDto = permissionMap.get(parentId);
                if (Objects.isNull(cmsPermissionDto) && Objects.nonNull(excludeId) && parentId.compareTo(excludeId) == 0) {
                    return;
                }
                List<CmsPermissionDto> children = cmsPermissionDto.getChildren();
                if (CollectionUtils.isEmpty(children)) {
                    children = Lists.newArrayList();
                }
                children.add(x);
                children.sort(Comparator.comparing(CmsPermissionDto::getPriority));
                cmsPermissionDto.setChildren(children);
            }
        });
        permissionList.sort(Comparator.comparing(CmsPermissionDto::getPriority));
        return permissionList;
    }
}
