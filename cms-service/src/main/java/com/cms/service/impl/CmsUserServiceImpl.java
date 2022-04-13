package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsProperties;
import com.cms.context.utils.UtilsShiro;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.mapper.CmsUserMapper;
import com.cms.dao.mapper.CmsUserRoleMapper;
import com.cms.service.api.CmsUserRoleService;
import com.cms.service.api.CmsUserService;
import com.cms.service.converter.CmsRoleConverter;
import com.cms.service.converter.CmsUserConverter;
import com.cms.service.converter.CmsUserRoleConverter;
import com.cms.service.dto.CmsUserDto;
import com.cms.service.dto.CmsUserRoleDto;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CmsUserServiceImpl implements CmsUserService {

    @Autowired
    private CmsUserMapper cmsUserMapper;
    @Autowired
    private UtilsProperties utilsProperties;
    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;
    @Autowired
    private CmsUserRoleService cmsUserRoleService;

    @Override
    public CmsUserDto selectByUsername(String username) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.getByUsername(username));
    }

    @Override
    public CmsUserDto selectByEmail(String email) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.getByEmail(email));
    }

    @Override
    public void updateLoginCount(Integer id) {
        cmsUserMapper.updateLoginCount(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CmsUserDto dto) {
        String salt = UtilsShiro.generateSalt();
        dto.setSalt(salt);
        dto.setAdmin(true);
        String password = dto.getPassword();
        dto.setPassword(UtilsShiro.sha256(password,salt,Integer.parseInt(utilsProperties.getPropertiesValue("shiro.hash.iterations"))));
        dto.setRegisterTime(LocalDateTime.now());
        CmsUserEntity cmsUserEntity = CmsUserConverter.CONVERTER.dtoToEntity(dto);
        cmsUserMapper.save(cmsUserEntity);
        Integer roleId = dto.getRoleId();
        if(Objects.nonNull(roleId)){
            CmsUserRoleDto cmsUserRoleDto = new CmsUserRoleDto();
            cmsUserRoleDto.setRoleId(roleId);
            cmsUserRoleDto.setUserId(cmsUserEntity.getId());
            cmsUserRoleService.save(cmsUserRoleDto);
        }
    }

    @Override
    public void deleteById(Integer id) {
        cmsUserMapper.deleteById(id);
    }

    @Override
    public void update(CmsUserDto dto) {
        String password = dto.getPassword();
        if(Objects.nonNull(password)){
            String salt = UtilsShiro.generateSalt();
            dto.setSalt(salt);
            dto.setPassword(UtilsShiro.sha256(password,salt,Integer.parseInt(utilsProperties.getPropertiesValue("shiro.hash.iterations"))));
        }
        Integer userId = dto.getId();
        cmsUserMapper.update(CmsUserConverter.CONVERTER.dtoToEntity(dto));
        cmsUserRoleService.deleteById(userId);
        Integer roleId = dto.getRoleId();
        if(Objects.nonNull(roleId)){
            CmsUserRoleDto cmsUserRoleDto = new CmsUserRoleDto();
            cmsUserRoleDto.setRoleId(roleId);
            cmsUserRoleDto.setUserId(userId);
            cmsUserRoleService.save(cmsUserRoleDto);
        }
    }

    @Override
    public CmsUserDto getById(Integer id) {
        CmsUserDto cmsUserDto = CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.selectById(id));
        Integer roleId = cmsUserRoleMapper.selectByUserId(id);
        if(Objects.nonNull(roleId)){
            cmsUserDto.setRoleId(roleId);
        }
        return cmsUserDto;
    }

    @Override
    public Page<CmsUserDto> getPage(CmsUserDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsUserConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsUserEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).
                doSelectPage(() -> cmsUserMapper.selectBySearchProvider(of));
        return new Page<>(page.getTotal(),CmsUserConverter.CONVERTER.entityToDto(page.getResult()));
    }
}
