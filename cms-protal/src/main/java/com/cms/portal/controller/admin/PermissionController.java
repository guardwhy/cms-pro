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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author guardwhy
 * @date 2022/3/21 21:13
 */
@Controller
@Validated
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
    public String toAdd(Model model,Integer parentId){
        // 条件判断
        if(Objects.nonNull(parentId)){
            model.addAttribute("parentId", parentId);
        }
        // 自动实例化一个Model对象用于向视图中传值
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission", "add");
    }

    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加权限")
    @DoValid
    public Result<String> doAdd(CmsPermissionDto cmsPermissionDto, BindingResult result){
        // cmsPermissionService.save(cmsPermissionDto);
        // 返回成功
        return Result.success();
    }

    /***
     * 修改时显示树形菜单数据
     * @return
     */
    @PostMapping("selectTree.do")
    @ResponseBody
    public Result doSelectTree(Integer excludeId){
        List<CmsPermissionDto> cmsPermissionDtos = buildData();
        // 存放所有数据
        Map<Integer, CmsPermissionDto> permissionMap = Maps.newHashMap();
        // 只存放parentId = 0的数据
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        // 循环数据，进行处理
        cmsPermissionDtos.forEach(x->{
            // 拿到id值
            Integer id = x.getId();
            //如果当前id 等于 排除的id跳过
            if (Objects.nonNull(excludeId) && id.compareTo(excludeId) ==0){
                return;
            }
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
                if(Objects.isNull(cmsPermissionDto) && Objects.nonNull(excludeId) && parentId.compareTo(excludeId)==0){
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
        return Result.success((ArrayList)permissionList);
    }

    /***
     * 首页权限数据
     * @param cmsPermissionDto
     * @return
     */
    @GetMapping("list.do")
    @ResponseBody
    public Result doList(CmsPermissionDto cmsPermissionDto){
        return Result.success((ArrayList)cmsPermissionService.getList(cmsPermissionDto));
    }

    /***
     * 修改权限页面数据回显
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit.do")
    public String toEdit(Integer id, Model model){
        model.addAttribute("data", cmsPermissionService.getById(id));
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission", "edit");
    }

    /***
     * 根据id删除用户权限
     * @param id
     * @return
     */
    @PostMapping("delete.do")
    @ResponseBody
    public Result doDelete(@NotNull (message = "请传递id") Integer id){
        // 根据id删除
        cmsPermissionService.deleteById(id);
        return Result.success();
    }

    public List<CmsPermissionDto> buildData(){
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        //4条数据
        CmsPermissionDto cmsPermissionDto1 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto2 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto3 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto4 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto5 = new CmsPermissionDto();

        cmsPermissionDto1.setId(1);
        cmsPermissionDto2.setId(2);
        cmsPermissionDto3.setId(3);
        cmsPermissionDto4.setId(4);
        cmsPermissionDto5.setId(5);

        cmsPermissionDto1.setName("测试1");
        cmsPermissionDto2.setName("测试2");
        cmsPermissionDto3.setName("测试3");
        cmsPermissionDto4.setName("测试4");
        cmsPermissionDto5.setName("测试5");

        cmsPermissionDto1.setParentId(0);
        cmsPermissionDto2.setParentId(1);
        cmsPermissionDto3.setParentId(2);
        cmsPermissionDto4.setParentId(3);
        cmsPermissionDto5.setParentId(0);

        permissionList.add(cmsPermissionDto1);
        permissionList.add(cmsPermissionDto2);
        permissionList.add(cmsPermissionDto3);
        permissionList.add(cmsPermissionDto4);
        permissionList.add(cmsPermissionDto5);
        return permissionList;
    }
}
