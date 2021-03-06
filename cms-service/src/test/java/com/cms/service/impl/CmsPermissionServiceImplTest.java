package com.cms.service.impl;

import com.cms.service.dto.CmsPermissionDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/3/24 10:47
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class CmsPermissionServiceImplTest {

    @Test
    public void test(){
        List<CmsPermissionDto> cmsPermissionDtos = buildData();
        // 存放所有数据
        Map<Integer, CmsPermissionDto> permissionMap = Maps.newHashMap();
        // 只存放parentId = 0的数据
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        // 循环数据，进行处理
        cmsPermissionDtos.forEach(x->{
            // 拿到id值
            Integer id = x.getId();
            // 放入Map集合中
            permissionMap.put(id, x);
            // 获取当前dto的父类id
            Integer parentId = x.getParentId();
            // 判断当前是否是顶级菜单
            if(parentId == 0){
                permissionList.add(x);
            }else{
                CmsPermissionDto cmsPermissionDto = permissionMap.get(parentId);
                List<CmsPermissionDto> children = cmsPermissionDto.getChildren();
                if(CollectionUtils.isEmpty(children)){
                     children = Lists.newArrayList();
                }
                // 子类添加操作
                children.add(x);
                cmsPermissionDto.setChildren(children);
            }
        });
        log.info("success");
    }

    @Test
    public void edit(){
        List<CmsPermissionDto> cmsPermissionDtos = buildData();
        // 存放所有数据
        Map<Integer, CmsPermissionDto> permissionMap = Maps.newHashMap();
        // 只存放parentId = 0的数据
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        // 在修改的时候Id为3的要排除掉
        Integer excludeId = 3;
        // 循环数据，进行处理
        cmsPermissionDtos.forEach(x->{
            // 拿到id值
            Integer id = x.getId();
            // 放入Map集合中
            permissionMap.put(id, x);
            // 获取当前dto的父类id
            Integer parentId = x.getParentId();
            // 判断当前是否是顶级菜单
            if(parentId == 0){
                permissionList.add(x);
            }else{
                CmsPermissionDto cmsPermissionDto = permissionMap.get(parentId);
                // 条件判断
                if(Objects.isNull(cmsPermissionDto) && parentId.compareTo(excludeId) == 0){
                    return;
                }
                List<CmsPermissionDto> children = cmsPermissionDto.getChildren();
                if(CollectionUtils.isEmpty(children)){
                    children = Lists.newArrayList();
                }
                // 子类添加操作
                children.add(x);
                cmsPermissionDto.setChildren(children);
            }
        });
        log.info("success");
    }


    public List<CmsPermissionDto> buildData(){
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        //4条数据
        CmsPermissionDto cmsPermissionDto1 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto2 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto3 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto4 = new CmsPermissionDto();

        cmsPermissionDto1.setId(1);
        cmsPermissionDto2.setId(2);
        cmsPermissionDto3.setId(3);
        cmsPermissionDto4.setId(4);

        cmsPermissionDto1.setName("测试1");
        cmsPermissionDto2.setName("测试2");
        cmsPermissionDto3.setName("测试3");
        cmsPermissionDto4.setName("测试4");

        cmsPermissionDto1.setParentId(0);
        cmsPermissionDto2.setParentId(1);
        cmsPermissionDto3.setParentId(2);
        cmsPermissionDto4.setParentId(3);

        permissionList.add(cmsPermissionDto1);
        permissionList.add(cmsPermissionDto2);
        permissionList.add(cmsPermissionDto3);
        permissionList.add(cmsPermissionDto4);
        return permissionList;
    }
}
