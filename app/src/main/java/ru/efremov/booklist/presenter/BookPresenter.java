package ru.efremov.booklist.presenter;

import android.content.Context;
import io.reactivex.annotations.NonNull;
import ru.efremov.booklist.common.UrlConfig;
import ru.efremov.booklist.common.base.BasePresenter;
import ru.efremov.booklist.common.manager.ToastManager;
import ru.efremov.booklist.common.net.MyObserver;
import ru.efremov.booklist.common.net.NetWorks;
import ru.efremov.booklist.entity.HnBook;
import ru.efremov.booklist.impl.BookView;
import ru.efremov.booklist.model.BaseModel;
import ru.efremov.booklist.model.BookDetailResultModel;
import ru.efremov.booklist.model.BookQueryParam;
import ru.efremov.booklist.model.DelBook;
import ru.efremov.booklist.model.Search;
import ru.efremov.booklist.model.SearchModel;


/* 店内商品列表 */
public class BookPresenter extends BasePresenter {

    private BookView bookView;

    public BookView getBooksView() {
        return bookView;
    }

    public void setMallGoodsView(BookView bookView) {
        this.bookView = bookView;
    }

    public BookPresenter(Context context) {
        super(context);
    }

    @Override
    protected void detachView() {
        bookView = null;
    }

    /**
     * 获取书籍列表
     *
     * @param
     * @param currentPage 当前访问页数
     * @param pageSize    每页获取的商品个数
     */
    public void getBooksList(final Context context, String keyword, int currentPage, String pageSize) {
        BookQueryParam search = new BookQueryParam();

        search.setCurrentPage(String.valueOf(currentPage));
        search.setPageSize(pageSize);

        NetWorks.getInstance().getBookList(context, search, new MyObserver<SearchModel>() {
            @Override
            public void onNext(@NonNull SearchModel result) {
                try {
                    if (UrlConfig.RESULT_OK == result.getState()) {
//                        Log.w("获取书籍列表", "成功。但是有没有书籍还不一定");
//                        List<MallGoodsWPBLRecords> mallGoodsWPBLRecords = result.getRecords();
                        bookView.onGetBooksData(result/*result.getRecords()*/);
                    } else {
                        ToastManager.showToast(context, result.getMsg());
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
     * 增加书籍
     *
     * @param
     * @param
     */
    public void addBook(final Context context, HnBook book) {

        NetWorks.getInstance().addBook(context, book, new MyObserver<BaseModel>() {
            @Override
            public void onNext(@NonNull BaseModel result) {
                try {
                    if (UrlConfig.RESULT_OK == result.getState()) {
                        bookView.onAddBookData(result);
                    } else {
                        ToastManager.showToast(context, result.getMsg());
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
     * 编辑书籍
     *
     * @param
     * @param
     */
    public void editBook(final Context context, HnBook book) {

        NetWorks.getInstance().editBook(context, book, new MyObserver<BaseModel>() {
            @Override
            public void onNext(@NonNull BaseModel result) {
                try {
                    if (UrlConfig.RESULT_OK == result.getState()) {
//                        Log.w("获取门店内商品列表", "成功。但是有没有商品还不一定");
//                        List<MallGoodsWPBLRecords> mallGoodsWPBLRecords = result.getRecords();
                        bookView.onEditBookData(result/*result.getRecords()*/);
                    } else {
                        ToastManager.showToast(context, result.getMsg());
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
     * 删除书籍
     *
     * @param
     * @param
     */
    public void deleteBookById(final Context context, String bookId) {
        DelBook delBook=new DelBook();
        delBook.setId(bookId);

        NetWorks.getInstance().deleteBookById(context, delBook, new MyObserver<BaseModel>() {
            @Override
            public void onNext(@NonNull BaseModel result) {
                try {
                    if (UrlConfig.RESULT_OK == result.getState()) {
//                        Log.w("获取门店内商品列表", "成功。但是有没有商品还不一定");
//                        List<MallGoodsWPBLRecords> mallGoodsWPBLRecords = result.getRecords();
                        bookView.onDeleteBookData(result/*result.getRecords()*/);
                    } else {
                        ToastManager.showToast(context, result.getMsg());
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
     * 删除书籍
     *
     * @param
     * @param
     */
    public void getBookDetailById(final Context context, String bookId) {
        DelBook query = new DelBook();

        query.setId(bookId);

        NetWorks.getInstance().queryBookById(context, query, new MyObserver<BookDetailResultModel>() {
            @Override
            public void onNext(@NonNull BookDetailResultModel result) {
                try {
                    if (UrlConfig.RESULT_OK == result.getState()) {
//                        Log.w("获取门店内商品列表", "成功。但是有没有商品还不一定");
//                        List<MallGoodsWPBLRecords> mallGoodsWPBLRecords = result.getRecords();
                        bookView.onQueryBookByIdData(result/*result.getRecords()*/);
                    } else {
                        ToastManager.showToast(context, result.getMsg());
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
