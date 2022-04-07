package com.cms.core.foundation;

import java.io.Serializable;
import java.util.List;

/**
 * @author guardwhy
 * @date 2022/4/6 23:32
 * 分页
 */
public class Page<T> implements Serializable {
    /***
     * 分页条数
     */
    private Long pageCount;

    /***
     * 内容
     */
    private List<T> content;

    public Page(Long pageCount, List<T> content) {
        this.pageCount = pageCount;
        this.content = content;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
