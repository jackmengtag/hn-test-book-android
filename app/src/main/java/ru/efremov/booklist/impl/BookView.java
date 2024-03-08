package ru.efremov.booklist.impl;



import java.util.List;

import ru.efremov.booklist.model.BaseModel;
import ru.efremov.booklist.model.SearchModel;

public interface BookView {
    void onGetBooksData(SearchModel result);

    void onAddBookData(BaseModel result);

    void onEditBookData(BaseModel result);

    void onDeleteBookData(BaseModel result);

    void onDeleteBatchBookData(BaseModel result);

    void onQueryBookByIdData(BaseModel result);
}
