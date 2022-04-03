package com.cms.core.foundation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author guardwhy
 * @date 2022/4/3 10:34
 */
public class TreeDto<DTO,PK extends Serializable> extends BaseDto<PK>{
    private List<DTO> children;
    private List<Map<String,String>> checkArr;

    public List<DTO> getChildren() {
        return children;
    }

    public void setChildren(List<DTO> children) {
        this.children = children;
    }

    public List<Map<String, String>> getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(List<Map<String, String>> checkArr) {
        this.checkArr = checkArr;
    }
}
