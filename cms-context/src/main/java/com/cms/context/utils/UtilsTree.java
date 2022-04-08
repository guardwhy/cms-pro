package com.cms.context.utils;

import com.cms.core.foundation.TreeDto;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author guardwhy
 * @date 2022/4/3 10:57
 */
public class UtilsTree {
    /**
     * 递归给树形结构添加checkArr复选框
     * @param permissionList          数据
     * @param <PK>                    主键
     * @param <T>                     实体
     */
    public static<PK extends Serializable,T extends TreeDto<T,PK>> void recursion(List<T> permissionList, List<PK> checkedIds){
        permissionList.forEach(x->{
            List<Map<String, String>> checked = Collections.singletonList(Collections.singletonMap("checked", "0"));
            if(org.apache.commons.collections.CollectionUtils.isNotEmpty(checkedIds)){
                if(checkedIds.contains(x.getId())){
                    checked = Collections.singletonList(Collections.singletonMap("checked", "1"));
                }
            }
            x.setCheckArr(checked);
            if(!CollectionUtils.isEmpty(x.getChildren())){
                recursion(x.getChildren(),checkedIds);
            }
        });
    }
}
