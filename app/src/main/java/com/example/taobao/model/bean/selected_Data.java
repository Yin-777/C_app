
package com.example.taobao.model.bean;


import java.util.List;

public class selected_Data {

    private List<selected_list> list;
    private int total;
    private int pageSize;
    private int currentPage;
    private boolean hasNext;
    private boolean hasPre;
    private int totalPage;

    public void setList(List<selected_list> list) {
        this.list = list;
    }

    public List<selected_list> getList() {
        return list;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean getHasNext() {
        return hasNext;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public boolean getHasPre() {
        return hasPre;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

}