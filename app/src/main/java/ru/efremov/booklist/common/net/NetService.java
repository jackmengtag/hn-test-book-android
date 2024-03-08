package ru.efremov.booklist.common.net;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ru.efremov.booklist.entity.HnBook;
import ru.efremov.booklist.model.BaseModel;
import ru.efremov.booklist.model.BookDetailResultModel;
import ru.efremov.booklist.model.BookQueryParam;
import ru.efremov.booklist.model.DelBook;
import ru.efremov.booklist.model.DelListBook;
import ru.efremov.booklist.model.SearchModel;

/**
 * artifact  接口
 */

interface NetService {

    @POST("/book/hnBook/add")
    Observable<BaseModel> addBook(@Body HnBook book);//

    @POST("/book/hnBook/edit")
    Observable<BaseModel> editBook(@Body HnBook book);//

    @POST("/book/hnBook/delete")
    Observable<BaseModel> delBook(@Body DelBook delBook);

    @POST("/book/hnBook/deleteBatch")
    Observable<BaseModel> deleteBatch(@Body DelListBook delListBook);//

    @POST("/book/hnBook/queryById")
    Observable<BookDetailResultModel> queryById(@Body DelBook delBook);//

    @POST("/book/hnBook/list")
    Observable<SearchModel> getBookList(@Body BookQueryParam queryParam);//


}
