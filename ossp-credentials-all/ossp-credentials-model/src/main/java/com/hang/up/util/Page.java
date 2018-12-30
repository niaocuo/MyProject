package com.hang.up.util;

import java.util.Map;

/**
 * Created by 顺 on 2018/12/11.
 */
public class Page<T> {
    private int currentPage = 1;    //当前页数
    private int total;            //记录总行数
    private int pageSize = 5;    //每页记录行数
    private Map queryCondition;
    private T tableData;              //返回的具体数据

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getTableData() {
        return tableData;
    }

    public void setTableData(T tableData) {
        this.tableData = tableData;
    }

    public Map getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(Map queryCondition) {
        this.queryCondition = queryCondition;
    }
}
