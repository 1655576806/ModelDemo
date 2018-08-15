package com.example.shuiyirenjian.youmu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.listener.MessagesQueryListener;
import cn.bmob.v3.exception.BmobException;

public class ChatActivity extends AppCompatActivity {
    BmobIMConversation messageManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageManager.queryMessages(null, 10, new MessagesQueryListener() {
            @Override
            public void done(List<BmobIMMessage> list, BmobException e) {
//                sw_refresh.setRefreshing(false);
//                if (e == null) {
//                    if (null != list && list.size() > 0) {
//                        adapter.addMessages(list);
//                        adapter.notifyDataSetChanged();
//                        layoutManager.scrollToPositionWithOffset(list.size() - 1, 0);
//                    }
//                } else {
//                    toast(e.getMessage() + "(" + e.getErrorCode() + ")");
//                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO 消息：5.1、根据会话入口获取消息管理，在聊天页面以及发送添加好友和同意添加好友请求时使用 
        if(messageManager==null){


//            messageManager = BmobIMConversation.obtain(BmobIMClient.getInstance(), conversationEntrance);
        }


    }
}
