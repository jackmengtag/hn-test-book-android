package com.yangna.lbdsp.book.presenter;

import android.content.Context;

import com.yangna.lbdsp.book.bean.HnBook;
import com.yangna.lbdsp.book.impl.BookListView;
import com.yangna.lbdsp.book.model.BookBaseModel;
import com.yangna.lbdsp.book.model.BookBaseResult;
import com.yangna.lbdsp.book.model.BookDetailResultModel;
import com.yangna.lbdsp.book.model.BookListModel;
import com.yangna.lbdsp.book.param.DeleteListParam;
import com.yangna.lbdsp.book.param.DeleteParam;
import com.yangna.lbdsp.book.param.QueryDetailParam;
import com.yangna.lbdsp.book.param.QueryListParam;
import com.yangna.lbdsp.common.UrlConfig;
import com.yangna.lbdsp.common.base.BaseModel;
import com.yangna.lbdsp.common.base.BasePresenter;
import com.yangna.lbdsp.common.manager.ToastManager;
import com.yangna.lbdsp.common.net.MyObserver;
import com.yangna.lbdsp.common.net.NetWorks;

public class BookListPresenter extends BasePresenter {

    //主页回调
    private BookListView bookListView;

    public BookListPresenter(Context context) {
        super(context);
    }

    @Override
    protected void detachView() {
        bookListView = null;
    }

    public void setBookListView(BookListView bookListView) {
        this.bookListView = bookListView;
    }


    /**
     *获取书籍详情
     */
    public void getBookDetailById(final Context context,String id) {
//      Map<String, String> map = UrlConfig.getCommonMap();
//      map.put("token", BaseApplication.getInstance().getUserId());
        QueryDetailParam requestParam = new QueryDetailParam();
        requestParam.setId(id);

        NetWorks.getInstance().getBookDetailById(context, requestParam, new MyObserver<BookDetailResultModel>() {
            @Override
            public void onNext(BookDetailResultModel model) {
                try {
                    if (UrlConfig.RESULT_OK == model.getState()) {
                        HnBook book=model.getBody();
                        bookListView.getBookDetailById(model);
                    } else {
                        ToastManager.showToast(context, model.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastManager.showToast(context, e);
            }
        });
    }


    /**
     *添加书籍
     */
    public void addBook(final Context context,HnBook book) {
//      Map<String, String> map = UrlConfig.getCommonMap();
//      map.put("token", BaseApplication.getInstance().getUserId());
//        HnBook book=new HnBook();

        NetWorks.getInstance().addBook(context, book, new MyObserver<BaseModel>() {
            @Override
            public void onNext(BaseModel model) {
                try {
                    if (UrlConfig.RESULT_OK == model.getState()) {
                        bookListView.addBook(model);
                    } else {
                        ToastManager.showToast(context, model.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastManager.showToast(context, e);
            }
        });
    }

    /**
     *更新书籍
     */
    public void updateBook(final Context context,HnBook book) {
//      Map<String, String> map = UrlConfig.getCommonMap();
//      map.put("token", BaseApplication.getInstance().getUserId());
//        QueryDetailParam requestParam = new QueryDetailParam();
//        requestParam.setId(id);

        NetWorks.getInstance().updateBook(context, book, new MyObserver<BaseModel>() {
            @Override
            public void onNext(BaseModel model) {
                try {
                    if (UrlConfig.RESULT_OK == model.getState()) {
//                        HnBook book=model.getBody();
                        bookListView.updateBook(model);
                    } else {
                        ToastManager.showToast(context, model.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastManager.showToast(context, e);
            }
        });
    }


    /**
     *删除书籍
     */
    public void deleteBookById(final Context context,String id) {
//      Map<String, String> map = UrlConfig.getCommonMap();
//      map.put("token", BaseApplication.getInstance().getUserId());
//        QueryDetailParam requestParam = new QueryDetailParam();
//        requestParam.setId(id);
        DeleteParam deleteParam=new DeleteParam();
        deleteParam.setId(id);

        NetWorks.getInstance().delBook(context, deleteParam, new MyObserver<BaseModel>() {
            @Override
            public void onNext(BaseModel model) {
                try {
                    if (UrlConfig.RESULT_OK == model.getState()) {
//                        HnBook book=model.getBody();
                        bookListView.deleteBook(model);
                    } else {
                        ToastManager.showToast(context, model.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastManager.showToast(context, e);
            }
        });
    }


    /**
     *批量删除书籍
     */
    public void deleteBookListByIds(final Context context,String ids) {
//      Map<String, String> map = UrlConfig.getCommonMap();
//      map.put("token", BaseApplication.getInstance().getUserId());
        DeleteListParam deleteListParam=new DeleteListParam();
        deleteListParam.setIds(ids);

        NetWorks.getInstance().deleteBookList(context, deleteListParam, new MyObserver<BaseModel>() {
            @Override
            public void onNext(BaseModel model) {
                try {
                    if (UrlConfig.RESULT_OK == model.getState()) {
//                        HnBook book=model.getBody();
                        bookListView.deleteBookList(model);
                    } else {
                        ToastManager.showToast(context, model.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastManager.showToast(context, e);
            }
        });
    }


    /**
     *获取书籍列表
     */
    public void getBookListByParam(final Context context) {
//      Map<String, String> map = UrlConfig.getCommonMap();
//      map.put("token", BaseApplication.getInstance().getUserId());
        QueryListParam queryListParam=new QueryListParam();
        queryListParam.setPageNo(1);
        queryListParam.setPageSize(10);

        NetWorks.getInstance().getBookList(context, queryListParam, new MyObserver<BookBaseModel>() {
            @Override
            public void onNext(BookBaseModel model) {
                try {
                    if (UrlConfig.RESULT_OK == model.getCode()) {
                        BookBaseResult bookModel=model.getResult();
                        bookListView.getBookList(bookModel.getRecords());
                    } else {
                        ToastManager.showToast(context, model.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastManager.showToast(context, e);
            }
        });
    }



}
