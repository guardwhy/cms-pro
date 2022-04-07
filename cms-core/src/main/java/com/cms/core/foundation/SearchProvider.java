package com.cms.core.foundation;

/**
 * @author guardwhy
 * @date 2022/4/7 20:18
 * 角色搜索提供类
 */
public class SearchProvider<T extends BaseEntity> {
    /**
     * 查询条件bean
     */
    private T criteria;
    /**
     * 排序字段
     */
    private String searchOrderBy;

    /**
     * get/set方法
     */
    public T getCriteria() {
        return criteria;
    }

    public void setCriteria(T criteria) {
        this.criteria = criteria;
    }

    public String getSearchOrderBy() {
        return searchOrderBy;
    }

    public void setSearchOrderBy(String searchOrderBy) {
        this.searchOrderBy = searchOrderBy;
    }

    /***
     * 静态方法 没有排序
     * @param criteria
     * @param <W>
     * @return
     */
    public static <W extends BaseEntity> SearchProvider of(W criteria){
        SearchProvider searchProvider = new SearchProvider<>();
        searchProvider.setCriteria(criteria);
        return searchProvider;
    }

    /***
     * 静态方法 自带排序
     * @param criteria
     * @param <W>
     * @return
     */
    public static <W extends BaseEntity> SearchProvider of(W criteria, String orderBy){
        SearchProvider searchProvider = new SearchProvider<>();
        searchProvider.setCriteria(criteria);
        searchProvider.setSearchOrderBy(orderBy);
        return searchProvider;
    }
}
