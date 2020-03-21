package com.zzd.api.dto;

import com.zzd.api.domain.BaseEntity;

import java.util.Collection;

/**
 * @author
 * @date
 * @describe
 */
public class PageResponseResult<T> extends BaseEntity {

    private int total;

    private Collection<T> items;

    private Object others;

    public PageResponseResult() {
    }

    public PageResponseResult(int total, Collection items) {
        this.total = total;
        this.items = items;
    }

    public Object getOthers() {
        return others;
    }

    public void setOthers(Object others) {
        this.others = others;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection getItems() {
        return items;
    }

    public void setItems(Collection items) {
        this.items = items;
    }
}
