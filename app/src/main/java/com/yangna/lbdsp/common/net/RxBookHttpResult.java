package com.yangna.lbdsp.common.net;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.yangna.lbdsp.book.model.BookBaseModel;
import com.yangna.lbdsp.common.UrlConfig;
import com.yangna.lbdsp.common.base.BaseApplication;
import com.yangna.lbdsp.common.base.BaseModel;
import com.yangna.lbdsp.common.manager.ToastManager;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Description:RxHttpResult
 */
class RxBookHttpResult<T extends BookBaseModel> implements Function<T, Observable<T>> {

    private Context context;


    private boolean NET_SESSION_KEY = false;// 用于限制多次弹出账号掉线对话框

    RxBookHttpResult(Context context) {
        this.context = context;
    }

    @Override
    public Observable<T> apply(@NonNull final T bean) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) {
                if (UrlConfig.SESSION_TIME_OUT == bean.getCode()) {
                    if (!NET_SESSION_KEY) {//防止多次弹框
                        timeOutToLogin();
                        NET_SESSION_KEY = true;
                    }
                } else {
                    NET_SESSION_KEY = false;
                    e.onNext(bean);
                    e.onComplete();
                }
            }
        });
    }

    //登录超时去登录页面
    private void timeOutToLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastManager.showToast(BaseApplication.getInstance(), "登录过期");
                        BaseApplication.getInstance().exitTokenLogin(context);
                    }
                });
            }
        }).start();
    }
}