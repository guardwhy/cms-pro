package com.cms.portal.controller.admin;

import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.dao.enums.PermissionTypeEnum;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.dto.CmsPermissionDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author guardwhy
 * @date 2022/3/21 21:13
 */
@Controller
@RequestMapping("permission")
public class PermissionController {
    // 注入权限表cmsPermissionService
    @Autowired
    private CmsPermissionService cmsPermissionService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("permission", "index");
    }

    @GetMapping("add.do")
    public String toAdd(Model model){
        // 获取权限类型枚举值
        PermissionTypeEnum[] values = PermissionTypeEnum.values();
        // 自动实例化一个Model对象用于向视图中传值
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission", "add");
    }

    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加权限")
    @DoValid
    public Result<String> doAdd(CmsPermissionDto cmsPermissionDto, BindingResult result){
        cmsPermissionService.save(cmsPermissionDto);
        // 返回成功
        return Result.success();
    }

    @PostMapping("selectTree.do")
    @ResponseBody
    public Result doSelectTree(){
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
        return Result.success((ArrayList)permissionList);
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
