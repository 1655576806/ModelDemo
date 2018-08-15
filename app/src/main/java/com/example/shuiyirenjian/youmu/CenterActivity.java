package com.example.shuiyirenjian.youmu;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shuiyirenjian.youmu.BaseNetEnty.MyConversation;
import com.example.shuiyirenjian.youmu.BaseNetEnty.MyUser;
import com.example.shuiyirenjian.youmu.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class CenterActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager mViewPaper;
    private List<View> pageViews;
    Button btn_pp;
    Button btn_msg;
    Button btn_fd;
    Button btn_st;

    private  int  ENTERCONVERSATION=0;
    @SuppressLint("HandlerLeak")
    Handler  centerHandler=new  Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bd = msg.getData();
            switch (bd.getInt("key")) {
                case 0:
                    //TODO 会话：4.1、创建一个常态会话入口，好友聊天，陌生人聊天
                  //  BmobIMConversation conversationEntrance = BmobIM.getInstance().startPrivateConversation(info, null);
                    //TODO 会话：4.2、查询全部会话
                 List<BmobIMConversation>   mlistCovs=BmobIM.getInstance().loadAllConversation();

                    NormalAdapter     recycleAdapter=new NormalAdapter(getData(mlistCovs));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(CenterActivity.this );
//设置布局管理器
                    mRecyclerView_cnvsat.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
                  //  mRecyclerView_cnvsat.setLayoutManager(new StaggeredGridLayoutManager(4,        StaggeredGridLayoutManager.VERTICAL));
//设置为垂直布局，这也是默认的
                    layoutManager.setOrientation(OrientationHelper. VERTICAL);
//设置Adapter
                    mRecyclerView_cnvsat.setAdapter(recycleAdapter);
                    //设置分隔线
//         recyclerView.addItemDecoration( new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//         recyclerView.addItemDecoration( );
//设置增加或删除条目的动画
                    mRecyclerView_cnvsat.setItemAnimator( new DefaultItemAnimator());

                    break;
            }
        }
    };

    private List<String> getData(List<BmobIMConversation> mlistCovs) {
        ArrayList  aldata=new ArrayList<String>();
        for (int i=0;i<mlistCovs.size();i++){
            BmobIMConversation  mbc=mlistCovs.get(i);
            MyConversation nmc=new MyConversation();
            nmc.setIcon(   mbc.getConversationIcon());
            nmc.setId(  mbc.getConversationIcon());
            nmc.setTitle( mbc.getConversationTitle());
            nmc.setType( mbc.getConversationType());
            aldata.add(mbc.getConversationTitle());
        }
        return aldata;

    }

    RecyclerView  mRecyclerView_cnvsat;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        mViewPaper = (ViewPager) findViewById(R.id.vp_center);
        btn_pp = (Button) findViewById(R.id.btn_pp);
        btn_msg = (Button) findViewById(R.id.btn_msg);
        btn_fd = (Button) findViewById(R.id.btn_fd);
        btn_st = (Button) findViewById(R.id.btn_st);

        btn_pp.setOnClickListener(this);
        btn_msg.setOnClickListener(this);
        btn_fd.setOnClickListener(this);
        btn_st.setOnClickListener(this);

        pageViews = new ArrayList<View>();
        LayoutInflater lInflater = getLayoutInflater();
        View ppview = lInflater.inflate(R.layout.layout_pp, null);

        View msgview = lInflater.inflate(R.layout.layout_msg, null);
        mRecyclerView_cnvsat=(RecyclerView)msgview.findViewById(R.id.recyclerView_cvsat);
        View fdview = lInflater.inflate(R.layout.layout_fd, null);

        View stview = lInflater.inflate(R.layout.layout_st, null);

        pageViews.add(ppview);
        pageViews.add(msgview);
        pageViews.add(fdview);
        pageViews.add(stview);

        mViewPaper.setAdapter(new MyPagerAdapter(pageViews));
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:

                        btn_pp.setBackgroundColor(Color.rgb(255, 255, 255));
                        btn_msg.setBackgroundColor(Color.rgb(255, 0, 0));
                        btn_fd.setBackgroundColor(Color.rgb(255, 0, 0));
                        btn_st.setBackgroundColor(Color.rgb(255, 0, 0));
                        break;
                    case 1:
                        btn_pp.setBackgroundColor(Color.rgb(255, 0, 0));
                        btn_msg.setBackgroundColor(Color.rgb(255, 255, 255));
                        btn_fd.setBackgroundColor(Color.rgb(255, 0, 0));
                        btn_st.setBackgroundColor(Color.rgb(255, 0, 0));
                        break;
                    case 2:
                        btn_pp.setBackgroundColor(Color.rgb(255, 0, 0));
                        btn_msg.setBackgroundColor(Color.rgb(255, 0, 0));
                        btn_fd.setBackgroundColor(Color.rgb(255, 255, 255));
                        btn_st.setBackgroundColor(Color.rgb(255, 0, 0));
                        break;
                    case 3:
                        btn_pp.setBackgroundColor(Color.rgb(255, 0, 0));
                        btn_msg.setBackgroundColor(Color.rgb(255, 0, 0));
                        btn_fd.setBackgroundColor(Color.rgb(255, 0, 0));
                        btn_st.setBackgroundColor(Color.rgb(255, 255, 255));
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onResume() {
        //TODO 连接：3.1、登录成功、注册成功或处于登录状态重新打开应用后执行连接IM服务器的操作
        super.onResume();
        final MyUser user = BmobUser.getCurrentUser(MyUser.class);
     try{
         if (!TextUtils.isEmpty(user.getObjectId())) {
             BmobIM.connect(user.getObjectId(), new ConnectListener() {
                 @Override
                 public void done(String uid, BmobException e) {
                     if (e == null) {
                         //连接成功
                         Toast.makeText(CenterActivity.this, "服务器连接成功", Toast.LENGTH_LONG).show();
                     } else {
                         //连接失败
                         String StringError = BaseUtil.ErrorInfoString(e.getErrorCode());
                         Toast.makeText(CenterActivity.this, StringError, Toast.LENGTH_LONG).show();

                     }
                 }
             });
             //TODO 连接：3.3、监听连接状态，可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
             BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
                 @Override
                 public void onChange(ConnectionStatus status) {


                 }
             });
         }
     }catch (Exception e){
         Toast.makeText(CenterActivity.this, "链接异常", Toast.LENGTH_LONG).show();
     }




    }

    @Override
    protected void onPause() {
        super.onPause();
        //TODO 连接：3.2、退出登录需要断开与IM服务器的连接
        if( BmobIM.getInstance()!=null){
            BmobIM.getInstance().disConnect();
            Toast.makeText(CenterActivity.this, "断开服务器连接", Toast.LENGTH_LONG).show();
        }

    }

    private void viewSetBackground(boolean b, boolean b1, boolean b2, boolean b3) {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pp:
                btn_pp.setBackgroundColor(Color.rgb(255, 255, 255));
                btn_msg.setBackgroundColor(Color.rgb(255, 0, 0));
                btn_fd.setBackgroundColor(Color.rgb(255, 0, 0));
                btn_st.setBackgroundColor(Color.rgb(255, 0, 0));
                mViewPaper.setCurrentItem(0);
                break;
            case R.id.btn_msg:
                btn_pp.setBackgroundColor(Color.rgb(255, 0, 0));
                btn_msg.setBackgroundColor(Color.rgb(255, 255, 255));
                btn_fd.setBackgroundColor(Color.rgb(255, 0, 0));
                btn_st.setBackgroundColor(Color.rgb(255, 0, 0));
                mViewPaper.setCurrentItem(1);
                BaseUtil.sendhandlermessage(centerHandler ,false,ENTERCONVERSATION,"");

                break;
            case R.id.btn_fd:
                btn_pp.setBackgroundColor(Color.rgb(255, 0, 0));
                btn_msg.setBackgroundColor(Color.rgb(255, 0, 0));
                btn_fd.setBackgroundColor(Color.rgb(255, 255, 255));
                btn_st.setBackgroundColor(Color.rgb(255, 0, 0));
                mViewPaper.setCurrentItem(2);
                break;
            case R.id.btn_st:
                btn_pp.setBackgroundColor(Color.rgb(255, 0, 0));
                btn_msg.setBackgroundColor(Color.rgb(255, 0, 0));
                btn_fd.setBackgroundColor(Color.rgb(255, 0, 0));
                btn_st.setBackgroundColor(Color.rgb(255, 255, 255));
                mViewPaper.setCurrentItem(3);
                break;

        }

    }
}