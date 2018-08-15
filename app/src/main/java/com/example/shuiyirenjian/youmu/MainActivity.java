package com.example.shuiyirenjian.youmu;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.DeviceUtils;
import com.example.shuiyirenjian.youmu.BaseNetEnty.MyUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public Button select_btn;
public Context mcontent;
public ImageView img_select;
public TextView tx_path;

    RecyclerView recyclerView;
    public Button upImg_btn;
    public Button mlogin_btn;
    private    NormalAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcontent=this;
        select_btn=(Button)findViewById(R.id.select_btn);
        select_btn.setOnClickListener((View.OnClickListener) this);
        tx_path=(TextView)findViewById(R.id.tx_path) ;
        img_select=(ImageView)findViewById(R.id.img_select);
        upImg_btn=(Button)findViewById(R.id.upImg_btn);
        upImg_btn.setOnClickListener(this);
        mlogin_btn=(Button)findViewById(R.id.login_btn);
        mlogin_btn.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

           recycleAdapter=new NormalAdapter(getData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
//设置布局管理器
      //  recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,        StaggeredGridLayoutManager.VERTICAL));
//设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
//设置Adapter
        recyclerView.setAdapter(recycleAdapter);
        //设置分隔线
//         recyclerView.addItemDecoration( new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//         recyclerView.addItemDecoration( );
//设置增加或删除条目的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator());



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add:
             
                recycleAdapter.addData(0);
                break;
            case R.id.remove:
                recycleAdapter.removeData(0);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.select_btn:
                select_btn.setEnabled(false);
/**
 *以带结果的方式启动Intent，这样就可以拿到图片地址
 */
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra("crop", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, 0);



                break;
            case R.id.upImg_btn:
                upImg_btn.setEnabled(false);

                Resources r =mcontent.getResources();
                Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + r.getResourcePackageName(R.mipmap.ic_launcherw) + "/"
                        + r.getResourceTypeName(R.mipmap.ic_launcherw) + "/"
                        + r.getResourceEntryName(R.mipmap.ic_launcherw));


                String picPath = uri.getPath() ;
                final BmobFile bmobFile = new BmobFile(new File(picPath));
                bmobFile.uploadblock(new UploadFileListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            //bmobFile.getFileUrl()--返回的上传文件的完整地址
                            toast("上传文件成功:" + bmobFile.getFileUrl());
                        }else{
                            toast("上传文件失败：" + e.getMessage());
                        }
                        upImg_btn.setEnabled(true);
                    }

                    @Override
                    public void onProgress(Integer value) {
                        // 返回的上传进度（百分比）
                    }
                });
                Boolean  stateRoot= DeviceUtils.isDeviceRooted();
                toast("设备root了么"+(stateRoot?"是":"否"));

                break;
            case R.id.login_btn:
                MyUser user = BmobUser.getCurrentUser(MyUser.class);
                if(user!=null){

                    startActivity(new Intent(MainActivity.this,CenterActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
                break;
        }

    }

    private void toast(String s) {
        Toast.makeText(mcontent,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //uri代表的就是图片在内容解析者所在的地址
        if (requestCode == 0 && data!=null) {
            Uri uri = data.getData();
            ContentResolver cr = mcontent.getContentResolver();
            try {
                Bitmap bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
                img_select.setImageBitmap(bmp);
                select_btn.setEnabled(true);

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = mcontent.getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                //picturePath就是图片在储存卡所在的位置
                String picturePath = cursor.getString(columnIndex);
                tx_path.setText(picturePath);
                cursor.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }else{
            select_btn.setEnabled(true);
            tx_path.setText("未选择");
        }
    }

    public List<String> getData() {
        ArrayList  aldata=new ArrayList<String>();
        for (int i=0;i<10;i++){
            aldata.add(i+"");
        }
        return aldata;
    }
}
