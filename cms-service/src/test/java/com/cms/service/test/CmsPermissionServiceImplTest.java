package com.cms.service.test;

import com.cms.dao.entity.CmsPermissionEntity;
import com.cms.dao.mapper.CmsPermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * @author guardwhy
 * @date 2022/3/24 9:27
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class CmsPermissionServiceImplTest {
    // 注入权限数据层
    @Mock
    private CmsPermissionMapper cmsPermissionMapper;
    @Test
    public void test(){
        when(cmsPermissionMapper.selectById(1)).thenReturn(new CmsPermissionEntity());
        CmsPermissionEntity cmsPermissionEntity = cmsPermissionMapper.selectById(1);
        log.info("entity=[{}]",cmsPermissionEntity);
    }
}
