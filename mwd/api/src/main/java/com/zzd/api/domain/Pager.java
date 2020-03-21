package com.zzd.api.domain;

/**
 * @author
 * @date
 * @describe
 */
public class Pager extends BaseEntity{
    private static final long serialVersionUID = -1118940681623636344L;

    private Integer page;

    private Integer pageSize;

    private Integer startNum;

    private String sort;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }
}
