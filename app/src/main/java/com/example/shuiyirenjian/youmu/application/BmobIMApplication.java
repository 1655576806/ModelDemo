package com.example.shuiyirenjian.youmu.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import cn.bmob.newim.BmobIM;

public class BmobIMApplication extends Application {



    public static ExecutorService serviceThreadPool = Executors.newFixedThreadPool (5);
    private static ReadWriteLock rwl ;
    public static  boolean istestRex=true;
    public     static  ReadWriteLock getReadlockCustom()
    {
        if(rwl==null){
            rwl= new ReentrantReadWriteLock();
        }
        return rwl;
    };
    @Override
    public void onCreate() {
        super.onCreate();
        //TODO 集成：1.8、初始化IM SDK，并注册消息接收器
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            BmobIM.init(getApplicationContext());
            BmobIM.registerDefaultMessageHandler(new DemoMessageHandler());
            // init it in the function of onCreate in ur Application
            Utils.init((Application) getApplicationContext() );
        }
    }

    /**
     * 获取当前运行的进程名
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}