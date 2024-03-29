package com.wwb.laobiao.usmsg.websocketclient;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wwb.laobiao.common.ChatUtil;
import com.wwb.laobiao.usmsg.websocketclient.adapter.Adapter_ChatMessage;
import com.wwb.laobiao.usmsg.websocketclient.bean.ChatUser;
import com.wwb.laobiao.usmsg.websocketclient.im.JWebSocketClient;
import com.wwb.laobiao.usmsg.websocketclient.im.JWebSocketClientService;
import com.wwb.laobiao.usmsg.websocketclient.modle.ChatMessage;
import com.wwb.laobiao.usmsg.websocketclient.util.Util;
import com.yangna.lbdsp.R;
import com.yangna.lbdsp.common.UrlConfig;
import com.yangna.lbdsp.common.manager.ToastManager;
import com.yangna.lbdsp.common.net.NetWorks;
import com.yangna.lbdsp.common.swiperefreshLayout.ToastUtil;
import com.yangna.lbdsp.login.bean.Agreeinfor;
import com.yangna.lbdsp.login.bean.DisAgreeinfor;
import com.yangna.lbdsp.login.model.AgreeModel;
import com.yangna.lbdsp.login.model.DisAgreeModel;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
//import com.yxc.websocketclientdemo.adapter.Adapter_ChatMessage;
//import com.yxc.websocketclientdemo.im.JWebSocketClient;
//import com.yxc.websocketclientdemo.im.JWebSocketClientService;
//import com.yxc.websocketclientdemo.modle.ChatMessage;
//import com.yxc.websocketclientdemo.util.Util;
//import com.qzy.laobiao.R;
//import com.qzy.laobiao.common.UrlConfig;
//import com.qzy.laobiao.common.manager.ToastManager;
//import com.qzy.laobiao.common.net.NetWorks;
//import com.qzy.laobiao.login.model.UserInfoModel;
//import com.qzy.laobiao.msg.model.XiaoxiLvModel;
//import com.qzy.laobiao.msg.model.XtXiaoxiModel;
//import com.qzy.laobiao.msg.websocketclient.adapter.Adapter_ChatMessage;
//import com.qzy.laobiao.msg.websocketclient.im.JWebSocketClient;
//import com.qzy.laobiao.msg.websocketclient.im.JWebSocketClientService;
//import com.qzy.laobiao.msg.websocketclient.modle.ChatMessage;
//import com.qzy.laobiao.msg.websocketclient.util.Util;
public class WebMainActivity extends AppCompatActivity implements View.OnClickListener, Adapter_ChatMessage.AdapterChatMessageInterface {
    private final static String TAG = WebMainActivity.class.getSimpleName();
    private Context mContext;
    private JWebSocketClient client;
    private JWebSocketClientService.JWebSocketClientBinder binder;
    private JWebSocketClientService jWebSClientService;
    private EditText et_content;
    private ListView listView;
    private Button btn_send;
    private ImageButton im_return;
    private TextView textView;//   \\
    private List<ChatMessage> chatMessageList = new ArrayList<>();//消息列表
    private Adapter_ChatMessage adapter_chatMessage;
    private ChatMessageReceiver chatMessageReceiver;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("MainActivity", "服务与活动成功绑定");
            binder = (JWebSocketClientService.JWebSocketClientBinder) iBinder;
            jWebSClientService = binder.getService();
            client = jWebSClientService.client;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("MainActivity", "服务与活动成功断开");
        }
    };
//    private XiaoxiLvModel xiaoxiLvModel;
    private ChatUser chatUser;

    @Override
    public void onsel(int rsv) {
            if(rsv==0)
            {
                onAgree();
            }
            else
            {
                onDisAgree();
            }

    }
    private void onDisAgree() {
        DisAgreeinfor user=new DisAgreeinfor();
        if(chatUser==null)
        {
            user.setdecide("19");
            user.setinvite("29");
            Log.e("MainActivity", "not user");
        }
        else
        {
            //chatmodel.getdecideAccountId()
            ///chatUser.getdecideAccountId();
            user.setdecideid(chatUser.getdecideid());
            user.setinviteid(chatUser.getinviteid());
        }
        Observer<DisAgreeModel> observer=new Observer<DisAgreeModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(DisAgreeModel loginModel) {
                Log.d(TAG,""+loginModel.getMsg());
                ToastManager.showToast(mContext, loginModel.getMsg());
                ChatUtil.getInstance().removeinvite(chatUser.getinviteid());
                onsend(loginModel.getMsg());
                onwebback();
                onBackPressed();
            }

            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onComplete() {

            }
        };
        NetWorks.getInstance().goDisAgree(mContext,user,observer);
    }
    private void onAgree() {
        Agreeinfor user=new Agreeinfor();
        if(chatUser!=null)
        {
            user.setdecideid(chatUser.getdecideid());
            user.setinviteid(chatUser.getinviteid());
        }
        else
        {
            user.setdecide("19");
            user.setinvite("29");
        }
        Observer<AgreeModel> observer=new Observer<AgreeModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AgreeModel loginModel) {
                Log.d(TAG,""+loginModel.getMsg());
                ToastManager.showToast(mContext, loginModel.getMsg());
                ChatUtil.getInstance().removeinvite(chatUser.getinviteid());
                onsend(loginModel.getMsg());
                onwebback();
                onBackPressed();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        NetWorks.getInstance().goAgree(mContext,user,observer);
    }

    private void onsend(String content) {
        if (client != null && client.isOpen()) {
            jWebSClientService.sendMsg(content);

            //暂时将发送的消息加入消息列表，实际以发送成功为准（也就是服务器返回你发的消息时）
            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setContent(content);
            chatMessage.setIsMeSend(1);
            chatMessage.setIsRead(0);
            chatMessage.setTime(System.currentTimeMillis()+"");
            chatMessageList.add(chatMessage);
            initChatMsgListView();
            et_content.setText("");
        } else {
            Util.showToast(mContext, "连接已断开，请稍等或重启App哟");
        }
    }
    private class ChatMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String message=intent.getStringExtra("message");
            if(message!=null)
            {
                ChatMessage chatMessage=new ChatMessage();
                chatMessage.setContent(message);
                chatMessage.setIsMeSend(0);
                chatMessage.setIsRead(1);
                chatMessage.setTime(System.currentTimeMillis()+"");
                chatMessageList.add(chatMessage);

                initChatMsgListView();
            }
//            for(int i=0;i<chatMessageList.size();i++)
            {
//                 ChatMessage chatMessaget= chatMessageList.get(i);
               // chatMessageList.get(i).setIsRead(1);
            }
            jWebSClientService.setrecflat(1);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide(); // activity_webmain
        setContentView(R.layout.activity_webmain);
        mContext= this;
        findViewById();
        initView();
        initmeinfor();
		activitytransfer();
		////        //启动服务
        startJWebSClientService();
//        //绑定服务
        bindService();
//        //注册广播
        doRegisterReceiver();
//        //检测通知是否开启
        checkNotification(mContext);
        if(chatUser!=null)
        {
            Log.d(TAG,"开始运行"+chatUser.ips+"  "+chatUser.tokens);
            ToastUtil.showToast("与"+chatUser.ips+"聊天");
        }

    }

    private void initmeinfor() {
        Map<String, String> map = UrlConfig.getCommonMap();
//        Observer<com.yangna.lbdsp.mine.model.UserInfoModel> observer=new ;
//        NetWorks.getInstance().getMineInfo(mContext,map,observer);
    }

    private void activitytransfer() {
         if(chatUser==null)
         {
          chatUser=new ChatUser();
         }
        Intent intent = getIntent();
        Bundle bundleExtra = intent.getBundleExtra("bundle");
        if(bundleExtra==null)
        {
            return;
        }
        chatUser= (ChatUser) bundleExtra.getSerializable(ChatUser.KEYTRACY);
        if(chatUser==null)
        {
            chatUser=new ChatUser();
        }
        if(chatUser.sky==2)
        {
            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setContent(chatUser.getinviteid()+"开始聊天"+chatUser.getdecideid());
            chatMessage.setIsMeSend(2);
            chatMessage.setIsRead(0);
            chatMessage.setTime(System.currentTimeMillis()+"");
            chatMessageList.add(chatMessage);
//            textView.setText(xiaoxiLvModel.getName()+"在请求升级");
            initChatMsgListView();
        }
        textView.setText(chatUser.title);

    }
    /**
     * 绑定服务
     */
    private void bindService() {
        Intent bindIntent = new Intent(mContext, JWebSocketClientService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }
    /**
     * 启动服务（websocket客户端服务）
     */
    private void startJWebSClientService() {
        Intent intent = new Intent(mContext, JWebSocketClientService.class);
//        if(0==1)
        {
            if(chatUser==null)
                {
                    chatUser=new ChatUser();
                }
            Bundle bundle=new Bundle();
            bundle.putSerializable(ChatUser.KEYTRACY, (Serializable) chatUser);
            intent.putExtra("bundle",bundle);//CmdLineActivity
        }
        startService(intent);
    }
    /**
     * 动态注册广播
     */
    private void doRegisterReceiver() {
        chatMessageReceiver = new ChatMessageReceiver();
        IntentFilter filter = new IntentFilter("com.xch.servicecallback.content");
        registerReceiver(chatMessageReceiver, filter);
    }


    private void findViewById() {
        listView = findViewById(R.id.chatmsg_listView);
        btn_send = findViewById(R.id.btn_send);
        et_content = findViewById(R.id.et_content);
        im_return = findViewById(R.id.iv_return);
        btn_send.setOnClickListener(this);
        im_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onwebback();
                onBackPressed();
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        //在这里执行操作
                    }
                }, 1000);
            }
        });
        textView = findViewById(R.id.tv_title);

    }

    private void onwebback() {
        if(jWebSClientService!=null)
        {
            jWebSClientService.setrecflat(-2);//closeConnect
            jWebSClientService.closeConnect();//
        }
        {
            Intent intent = getIntent();
            Bundle bundle = new Bundle();//csel
//            bundle.putInt(XiaoxiLvModel.CHAR_SEL,uscharsel );
//            bundle.putSerializable(XiaoxiLvModel.SER_KEY,xiaoxiLvModel);
            intent.putExtras(bundle);
            setResult(0,intent);
        }
    }
    private void initView() {
        //监听输入框的变化
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (et_content.getText().toString().length() > 0) {
                    btn_send.setVisibility(View.VISIBLE);
                } else {
                    btn_send.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                String content = et_content.getText().toString();
                if (content.length() <= 0) {
                    Util.showToast(mContext, "消息不能为空哟");
                    return;
                }

                if (client != null && client.isOpen()) {
                    jWebSClientService.sendMsg(content);

                    //暂时将发送的消息加入消息列表，实际以发送成功为准（也就是服务器返回你发的消息时）
                    ChatMessage chatMessage=new ChatMessage();
                    chatMessage.setContent(content);
                    chatMessage.setIsMeSend(1);
                    chatMessage.setIsRead(0);
                    chatMessage.setTime(System.currentTimeMillis()+"");
                    chatMessageList.add(chatMessage);
                    initChatMsgListView();
                    et_content.setText("");
                } else {
                    Util.showToast(mContext, "连接已断开，请稍等或重启App哟");
                }
                break;
            default:
                break;
        }
    }

    private void initChatMsgListView(){
        if(chatUser==null)
        {
            chatUser=new ChatUser();
        }
        adapter_chatMessage = new Adapter_ChatMessage(mContext, chatMessageList);
        adapter_chatMessage.setchatname(chatUser.getName(0));
        adapter_chatMessage.setchatIcon(chatUser.getIcon(0),0);
//        if(userInfoModel!=null)
        {
//            userInfoModel.getBody().
        }
        adapter_chatMessage.setchatIcon(chatUser.getIcon(0),0);
        listView.setAdapter(adapter_chatMessage);
        listView.setSelection(chatMessageList.size());
        adapter_chatMessage.setinterface(this);
    }


    /**
     * 检测是否开启通知
     *
     * @param context
     */
    private void checkNotification(final Context context) {
        if (!isNotificationEnabled(context)) {
            new AlertDialog.Builder(context).setTitle("温馨提示")
                    .setMessage("你还未开启系统通知，将影响消息的接收，要去开启吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setNotification(context);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
    }
    /**
     * 如果没有开启通知，跳转至设置界面
     *
     * @param context
     */
    private void setNotification(Context context) {
        Intent localIntent = new Intent();
        //直接跳转到应用通知设置的代码：
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            localIntent.putExtra("app_package", context.getPackageName());
            localIntent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.addCategory(Intent.CATEGORY_DEFAULT);
            localIntent.setData(Uri.parse("package:" + context.getPackageName()));
        } else {
            //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
        }
        context.startActivity(localIntent);
    }

    /**
     * 获取通知权限,监测是否开启了系统通知
     *
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean isNotificationEnabled(Context context) {

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
