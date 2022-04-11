package com.cms.service.impl;

import com.cms.context.utils.UtilsHttp;
import com.cms.context.utils.UtilsProperties;
import com.cms.context.utils.UtilsShiro;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.mapper.CmsUserMapper;
import com.cms.service.api.CmsUserRoleService;
import com.cms.service.api.CmsUserService;
import com.cms.service.converter.CmsUserConverter;
import com.cms.service.dto.CmsUserDto;
import com.cms.service.dto.CmsUserRoleDto;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/3/10 11:34
 */
@Service
public class CmsUserServiceImpl implements CmsUserService {
    // 注入持久层mapper
    @Autowired
    private CmsUserMapper cmsUserMapper;
    @Autowired
    private UtilsProperties utilsProperties;
    // 注入角色业务层
    @Autowired
    private CmsUserRoleService cmsUserRoleService;

    @Override
    public CmsUserDto selectByUsername(String username) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.getByUsername(username));
    }

    /***
     * 邮箱验证
     * @param email   邮箱
     * @return
     */
    @Override
    public CmsUserDto selectByEmail(String email) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.getByEmail(email));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CmsUserDto dto) {
        // 拿到密码盐
        String salt = UtilsShiro.generateSalt();
        // 设置密码盐
        dto.setSalt(salt);
        // 设置用户名
        dto.setAdmin(true);
        // 获得密码
        String password = dto.getPassword();
        // 设置密码
        dto.setPassword(UtilsShiro.sha256(password,salt,Integer.parseInt(utilsProperties
                .getPropertiesValue("shiro.hash.iterations"))));
        // 设置注册时间
        dto.setRegisterTime(LocalDateTime.now());
        CmsUserEntity cmsUserEntity = CmsUserConverter.CONVERTER.dtoToEntity(dto);
        // 保存用户Entity
        cmsUserMapper.save(cmsUserEntity);
        // 拿到角色ID
        Integer roleId = dto.getRoleId();
        // 判断角色id
        if(Objects.nonNull(roleId)){
            CmsUserRoleDto cmsUserRoleDto = new CmsUserRoleDto();
            cmsUserRoleDto.setRoleId(roleId);
            cmsUserRoleDto.setUserId(cmsUserEntity.getId());
            // 保护角色id
            cmsUserRoleService.save(cmsUserRoleDto);
        }
    }

    /***
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public Page<CmsUserDto> getPage(CmsUserDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsUserConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsUserEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(),
                pageInfo.getPageSize()).
                doSelectPage(() -> cmsUserMapper.selectBySearchProvider(of));
        return new Page<>(page.getTotal(),CmsUserConverter.CONVERTER.entityToDto(page.getResult()));
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsUserDto dto) {
        cmsUserMapper.update(CmsUserConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsUserDto getById(Integer id) {
        return null;
    }
}
