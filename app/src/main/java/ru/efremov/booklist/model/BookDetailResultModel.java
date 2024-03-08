package ru.efremov.booklist.model;


import ru.efremov.booklist.entity.HnBook;

public class BookDetailResultModel extends BaseModel {

    private HnBook body;

    public HnBook getBody() {
        return body;
    }

    public void setBody(HnBook body) {
        this.body = body;
    }
}
