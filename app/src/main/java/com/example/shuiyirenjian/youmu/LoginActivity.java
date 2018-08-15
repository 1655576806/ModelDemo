package com.example.shuiyirenjian.youmu;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.shuiyirenjian.youmu.BaseEnty.RexResult;
import com.example.shuiyirenjian.youmu.BaseNetEnty.MyUser;
import com.example.shuiyirenjian.youmu.application.BmobIMApplication;
import com.example.shuiyirenjian.youmu.util.BaseUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



    private EditText edt_phonenumber;
    private  EditText edt_password;
    private  EditText edt_nick;
    private  EditText edt_youjian;
    private Switch switch_sex;
    private SeekBar seekBar_age;
    private TextView tv_age;
    private TextView tv_sex;
    Button btn_register_reg;
    private TextView tv_number_reg;
    private TextView tv_pass_reg;
    private TextView tv_nick_reg;
    private TextView tv_youjian_reg;
    private ProgressDialog progressDialog;


    Boolean sexflag=true;//男
    int  userage=16;//16岁
    @SuppressLint("HandlerLeak")
    Handler ResHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bd = msg.getData();
            switch (bd.getInt("key")) {
                case 0:
                    progressDialog
                            = new ProgressDialog( LoginActivity.this);
                    progressDialog.setIcon(R.mipmap.ic_launcher);
                    progressDialog.setTitle("等待");
                    progressDialog.setMessage("正在注册....");
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);

                    final String  number=  edt_phonenumber.getText().toString();
                    final String  pass=  edt_password.getText().toString();
                    final String  nick=  edt_nick.getText().toString();
                    final String  youjian= edt_youjian.getText().toString();

                    final RexResult regexResult= BaseUtil.regexUser( number, pass, nick, youjian);
                    if(regexResult!=null&&!regexResult.isResult()){
                        progressDialog.setTitle("错误！");
                        String errorstring="";

                        if(regexResult.getErrorNumberString()!=null){
                            errorstring+="* "+regexResult.getErrorNumberString()+"\n";
                        }
                        if(regexResult.getErrorPassString() !=null){
                            errorstring+="* "+regexResult.getErrorPassString()+"\n";
                        }
                        if(regexResult.getErrorNikeString()!=null){
                            errorstring+="* "+regexResult.getErrorNikeString()+"\n";
                        }
                        if(regexResult.getErrorYouJianString()!=null){
                            errorstring+="* "+regexResult.getErrorYouJianString()+"\n";
                        }
                        progressDialog.setMessage(errorstring);
                    } ;
                    BmobIMApplication.serviceThreadPool.submit(new Runnable()
                    {

                        @Override
                        public void run()
                        {
                            try {
                                if(regexResult!=null&&!regexResult.isResult()){
                                    Thread.sleep(3000);
                                }else{

                                    final MyUser myuser = new MyUser();
                                    myuser.setUsername(number);
                                    myuser.setPassword(pass);
                                    myuser.setAge(userage);
                                    myuser.setNick(nick);
                                    myuser.setSex(sexflag);
                                    myuser.setPower(1);
                                    myuser.setMobilePhoneNumber(number);
                                    myuser.setEmail(youjian.trim());
                                    myuser.setIsline(false);
                                    //注册
                                    doregister(myuser,number,pass);




                                    Thread.sleep(4000);
                                }


                            }
                            catch (Exception
                                    e) {

                            }
                            finally{
                                if (!LoginActivity.this.isFinishing()) {
                                    progressDialog.dismiss();
                                    btn_register_reg.setEnabled(true);
                                }

                            }
                        }




                    }) ;

                    break;
                case 1:
                    break;
            }
        }
    };

    private void doregister(MyUser myuser, final String number, final String pass) {
        myuser.signUp(new SaveListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if(e==null){
                    BmobUser bu2 = new BmobUser();
                    bu2.setUsername(number);
                    bu2.setPassword(pass);
                    bu2.login(new SaveListener<BmobUser>() {

                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if(e==null){
//                                toast("登录成功:");
                                Intent itCenten=new Intent(LoginActivity.this,CenterActivity.class);
                                startActivity(itCenten);
                                //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                                //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                            }else{
                                progressDialog.setMessage("登陆失败！="+  BaseUtil.ErrorInfoString(e.getErrorCode()));
                            }
                        }
                    });
                }else{
                    progressDialog.setMessage("注册失败！="+  BaseUtil.ErrorInfoString(e.getErrorCode()));
                }
            }

        });

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        tv_number_reg=     (TextView)findViewById(R.id.tv_number_reg);
        tv_pass_reg=     (TextView)findViewById(R.id.tv_pass_reg);
        tv_nick_reg=     (TextView)findViewById(R.id.tv_nick_reg);
        tv_youjian_reg=     (TextView)findViewById(R.id.tv_youjian_reg);

        edt_phonenumber=(EditText)findViewById(R.id.edt_phonenumber);
        edt_password=(EditText)findViewById(R.id.edt_password);
        edt_nick=(EditText)findViewById(R.id.edt_nick);
        edt_youjian=(EditText)findViewById(R.id.edt_youjian);

        tv_sex=     (TextView)findViewById(R.id.tv_sex);
        tv_sex.setText("性别:男");
        switch_sex=(Switch)findViewById(R.id.switch_sex);
        switch_sex. setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    tv_sex.setText("性别:女");
                    sexflag=false;
                } else {
                    tv_sex.setText("性别:男");;// 关闭蓝牙
                    sexflag=true;
                }
            }
        });
        tv_age=     (TextView)findViewById(R.id.tv_age);
        tv_age.setText("年龄："+16+"岁");

        seekBar_age=(SeekBar)findViewById(R.id.seekBar_age);
        // 设置拖动条改变监听器
        seekBar_age.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // 停止拖动的时候调用
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                //  int age = arg0.getProgress()+16;
                // tv_age.setText("年龄："+age+"岁");
            }

            // 开始拖动时调用
            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
                // tv_age.setText("开始调节");

            }

            // 显示的是当前的进度
            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                int age = arg0.getProgress()+16;
                tv_age.setText("年龄："+age+"岁");
                userage=age;
            }
        });
        btn_register_reg   =  (Button)findViewById(R.id.btn_register_reg);
        btn_register_reg.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register_reg:
                btn_register_reg.setEnabled(false);
                BaseUtil.sendhandlermessage(ResHandler,false,0,"");
                break;
        }
    }
}
