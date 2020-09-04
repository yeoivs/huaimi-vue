package com.ieng.huaimi.common.bean;

import java.io.Serializable;

public class PageNature<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int page = 1;
    private int size = 30;
    private T condition;

    public PageNature(){}

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public T getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "PageNature{" +
                "page=" + page +
                ", size=" + size +
                ", condition=" + condition +
                '}';
    }
}
