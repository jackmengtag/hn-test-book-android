package com.yangna.lbdsp.book.impl;

import com.yangna.lbdsp.book.bean.HnBook;
import com.yangna.lbdsp.book.model.BookBaseResult;
import com.yangna.lbdsp.book.model.BookDetailResultModel;
import com.yangna.lbdsp.book.model.BookListModel;
import com.yangna.lbdsp.common.base.BaseModel;

import java.util.List;
import java.util.Map;

public interface BookListView {

    public void getBookDetailById(BookDetailResultModel model);

    public void addBook(BaseModel model);//

    public void deleteBook(BaseModel model);//

    public void deleteBookList(BaseModel model);//

    public void getBookList(List<HnBook> bookList);//

    public void updateBook(BaseModel model);//

}
