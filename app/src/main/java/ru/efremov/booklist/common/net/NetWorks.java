package ru.efremov.booklist.common.net;

import android.content.Context;



import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.efremov.booklist.entity.HnBook;
import ru.efremov.booklist.model.BaseModel;
import ru.efremov.booklist.model.BookDetailResultModel;
import ru.efremov.booklist.model.BookQueryParam;
import ru.efremov.booklist.model.DelBook;
import ru.efremov.booklist.model.DelListBook;
import ru.efremov.booklist.model.SearchModel;

/**
 * artifact  NetWorks
 */

public class NetWorks extends RetrofitUtils {

    private static NetWorks instance;
    private NetService service;

    /**
     * 构造
     */
    private NetWorks() {
        service = getRetrofit().create(NetService.class);
    }

    /**
     * 单例
     */
    public static NetWorks getInstance() {
        if (instance == null) {
            synchronized (NetWorks.class) {
                if (instance == null) {
                    instance = new NetWorks();
                }
            }
        }
        return instance;
    }

    /**
     * 通用订阅
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    private <T extends BaseModel> void setSubscribe(Context context, Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .flatMap(new RxHttpResult<>(context))
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }



    public void addBook(Context context, HnBook book, MyObserver<BaseModel> observerx) {
        setSubscribe(context, service.addBook(book), observerx);
    }

    public void editBook(Context context, HnBook book, MyObserver<BaseModel> observerx) {
        setSubscribe(context, service.editBook(book), observerx);
    }

    public void deleteBookById(Context context, DelBook delBook, MyObserver<BaseModel> observerx) {
        setSubscribe(context, service.delBook(delBook), observerx);
    }

    public void deleteBatchBookByIds(Context context, DelListBook user, MyObserver<BaseModel> observerx) {
        setSubscribe(context, service.deleteBatch(user), observerx);
    }

    public void queryBookById(Context context, DelBook queryBook, MyObserver<BookDetailResultModel> observerx) {
        setSubscribe(context, service.queryById(queryBook), observerx);
    }

    //获取订单列表
    public void getBookList(Context context, BookQueryParam queryParam, Observer<SearchModel> observer) {
        setSubscribe(context, service.getBookList(queryParam), observer);
    }




}
