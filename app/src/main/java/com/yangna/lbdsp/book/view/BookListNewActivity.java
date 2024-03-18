package com.yangna.lbdsp.book.view;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yangna.lbdsp.R;
import com.yangna.lbdsp.book.adapter.BookListAdapter;
import com.yangna.lbdsp.book.adapter.BookPagerAdapter;
import com.yangna.lbdsp.book.bean.HnBook;
import com.yangna.lbdsp.book.impl.BookListView;
import com.yangna.lbdsp.book.model.BookBaseResult;
import com.yangna.lbdsp.book.model.BookDetailResultModel;
import com.yangna.lbdsp.book.model.BookListModel;
import com.yangna.lbdsp.book.presenter.BookListPresenter;
import com.yangna.lbdsp.common.base.BaseFragActivity;
import com.yangna.lbdsp.common.base.BaseModel;
import com.yangna.lbdsp.common.base.BasePresenterActivity;
import com.yangna.lbdsp.mall.impl.ClickListener;
import com.yangna.lbdsp.mall.impl.ShopPingCratView;
import com.yangna.lbdsp.mall.presenter.ShopPingCartPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 商品订单
 */
public class BookListNewActivity extends BasePresenterActivity<BookListPresenter> implements BookListView {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.no_data_ll)
    LinearLayout noDataLl;

    //Tab栏和滑动页关联适配器
    private BookListAdapter adapter;

    private BookListPresenter mPresenter;

    private static final String TAG = "BookListNewActivity";

    private int currentPage = 1;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_book_list_new;
    }

    @Override
    protected void initView() {
        super.initView();
        mPresenter.setBookListView(this);
        titleManager.setTitleTxt("书籍列表");
        titleManager.setLeftLayout(0, R.mipmap.back_left_img);

        ClickListener deleteClick = new ClickListener() {
            @Override
            public void onClick(String id) {
                mPresenter.deleteBookById(context,id);
            }
        };

        Log.e("BookListNewActivity","getBookList");
        adapter = new BookListAdapter(context,deleteClick);
        Log.e("BookListNewActivity","new BookListAdapter");
        lv.setAdapter(adapter);
        Log.e("BookListNewActivity","setAdapter");

        //将tab栏和滑动页关联起来
        mPresenter.getBookListByParam(context);
        Log.i(TAG, "getBookListByParam:" + "加载数据中.....");

        refresh.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mPresenter.getBookListByParam(context);
            }

            @Override
            public void onFinish() {
                refresh.setRefreshing(false);
            }
        }.start());


    }

    @Override
    protected BookListPresenter setPresenter() {
        mPresenter = new BookListPresenter(this);
        return mPresenter;
    }

    @Override
    public void getBookDetailById(BookDetailResultModel model) {

    }

    @Override
    public void addBook(BaseModel model) {

    }

    @Override
    public void deleteBook(BaseModel model) {

    }

    @Override
    public void deleteBookList(BaseModel model) {

    }

    @Override
    public void getBookList(List<HnBook> bookList) {
        if (bookList.size() <= 0 && currentPage == 1) {
            noDataLl.setVisibility(View.VISIBLE);
            refresh.setVisibility(View.GONE);
        } else {
            noDataLl.setVisibility(View.GONE);
            refresh.setVisibility(View.VISIBLE);

            if (1 == currentPage) {
//                adapter.notifyDataSetChanged();
                Log.e("BookListNewActivity","notifyDataSetChanged");
                adapter.setDates(bookList);
            }
//            else {
//                adapter.setDates(bookList);
//            }
            if (bookList != null && bookList.size() > 0) {
                currentPage++;
            }
        }
    }

    @Override
    public void updateBook(BaseModel model) {

    }
}
