package ru.efremov.booklist.entity;

public class SelBookInfo {
//    privateprivate long accountId;
//    public void setaccountId( long accountId) {
//        this.accountId=accountId;
//    }
////    public void setId( long adressId) {
////        this.Id=adressId;
////    }
    private String  currentPage;
    private String  pageSize;

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
