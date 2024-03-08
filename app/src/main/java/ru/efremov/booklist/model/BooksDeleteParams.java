package ru.efremov.booklist.model;

import java.util.List;

public class BooksDeleteParams {
    public List<String>ids;

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }
}
