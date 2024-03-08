package ru.efremov.booklist.model;

public class BookQueryParam {
    public String pageSize;
    public String currentPage;

    public String getPageSize() {
        return pageSize;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
