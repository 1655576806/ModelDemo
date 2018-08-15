package com.example.shuiyirenjian.youmu.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.shuiyirenjian.youmu.BaseEnty.RexResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BaseUtil {
	public static String getNowDate(String str){
		SimpleDateFormat myFmt2=new SimpleDateFormat(str);
		Date now=new Date();
		String strr=myFmt2.format(now);
		return strr;
	};
	public static String getDate(int sun){
		Date date=new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,sun);
		date=calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	};

	public static String getDateYear(){
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(date);
		return dateString+"-";
	};
	public static void sendhandlermessage(Handler mhandler, boolean flag, int key,
                                          Object str) {
		if (flag) {
			Looper.prepare();
		}
		Message msgs = new Message();
		Bundle data = new Bundle();
		data.putInt("key", key);
		msgs.obj = str;
		msgs.setData(data);
		mhandler.sendMessage(msgs);
		if (flag) {
			Looper.loop();
		}
	}
	public static void sendhandlermessage(Handler mhandler, boolean flag, int key,
                                          Object str, int delayMillis) {
		if (flag) {
			Looper.prepare();
		}
		Message msgs = new Message();
		Bundle data = new Bundle();
		data.putInt("key", key);
		msgs.obj = str;
		msgs.setData(data);
		mhandler.sendMessageDelayed(msgs, delayMillis);
		if (flag) {
			Looper.loop();
		}
	}
	public static void doProgressMessage(Handler mhander, int what, int i){
		Message msg=new Message();
		msg.what=what;
		msg.obj=i;
		mhander.sendMessage(msg);
	}
	public static void dotoast(Activity act, String str) {
//	Toast.makeText(act, str, 1000).show();
	}


	public static boolean isNetworkAvailable(Activity activity)
	{
		Context context = activity.getApplicationContext();
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null)
		{
			return false;
		}
		else
		{
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

			if (networkInfo != null && networkInfo.length > 0)
			{
				for (int i = 0; i < networkInfo.length; i++)
				{

					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean isNetWorkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
			}
		}

		return false;
	}


	public  static String getSystemTime(){
		SimpleDateFormat formatter   =   new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
		Date curDate =  new Date(System.currentTimeMillis());
		String str   =   formatter.format(curDate);
		return str;
	}
	public static  void goActivity(Context contexts, Class mThis) {
		Intent it = new Intent(contexts, mThis);
		contexts.startActivity(it);
	}
	public static  void goPzMsgActivity(Context contexts, Class mThis, String objectidPz) {
		Intent it = new Intent(contexts, mThis);
		it.putExtra("objId",objectidPz);
		contexts.startActivity(it);
	}
	public static  void gohisChatActivity(Context contexts, Class mThis, String objname) {
		Intent it = new Intent(contexts, mThis);
		it.putExtra("objname",objname);
		contexts.startActivity(it);
	}
	//	public static void sendhandlermessage(Handler mhandler, boolean flag, int key,
//										  Object str) {
//		if (flag) {
//			Looper.prepare();
//		}
//		Message msgs = new Message();
//		Bundle data = new Bundle();
//		data.putInt("key", key);
//		msgs.obj = str;
//		msgs.setData(data);
//		mhandler.sendMessage(msgs);
//		if (flag) {
//			Looper.loop();
//		}
//	}
	public static RexResult regexUser(String number, String pass ){
		RexResult  mRexResult=new RexResult();
		mRexResult.setResult(true);
		if(number==null||number!=null&&number.trim().equals("")){
			mRexResult.setErrorNumberString("手机号不能为空");
			mRexResult.setResult(false);
		}else if(number!=null && number.length()<10){
			mRexResult.setErrorNumberString("11位手机号错误 请正确输入");
			mRexResult.setResult(false);
		}
		if(pass==null||pass!=null&&pass.trim().equals("")){
			mRexResult.setErrorPassString("密码不能为空");
			mRexResult.setResult(false);
		}else if(pass!=null && pass.length()<6){
			mRexResult.setErrorPassString(" 密码太短错误！请正确输入大于6位密码");
			mRexResult.setResult(false);
		}

		return  mRexResult;

	}
	public static RexResult regexUser(String number, String pass, String nick, String youjian){
		RexResult  mRexResult=new RexResult();
		mRexResult.setResult(true);
		if(number==null||number!=null&&number.trim().equals("")){
			mRexResult.setErrorNumberString("手机号不能为空");
			mRexResult.setResult(false);
		}else if(number!=null && number.length()<10){
			mRexResult.setErrorNumberString("11位手机号错误 请正确输入");
			mRexResult.setResult(false);
		}
		if(pass==null||pass!=null&&pass.trim().equals("")){
			mRexResult.setErrorPassString("密码不能为空");
			mRexResult.setResult(false);
		}else if(pass!=null && pass.length()<6){
			mRexResult.setErrorPassString(" 密码太短错误！请正确输入大于6位密码");
			mRexResult.setResult(false);
		}
		if(nick==null||nick!=null&&nick.trim().equals("")){
			mRexResult.setErrorNikeString("昵称不能为空");
			mRexResult.setResult(false);
		}else if(nick!=null && nick.length()<1){
			mRexResult.setErrorNikeString(" 昵称太短错误！请正确输入大于1位");
			mRexResult.setResult(false);
		}
		if(youjian==null||youjian!=null&&youjian.trim().equals("")){
			mRexResult.setErrorYouJianString("邮箱不能为空");
			mRexResult.setResult(false);
		}else if(youjian!=null && youjian.length()<8||!youjian.contains("@")||!youjian.contains(".")){
			mRexResult.setErrorYouJianString(" 邮箱错误！请正确输入");
			mRexResult.setResult(false);
		}



		return  mRexResult;

	}
	public static String ErrorInfoString(int errorcode){
		String errorString="未知！网络连接异常";
		switch (errorcode){
			case  101 	:
				errorString="查询的 对象或Class 不存在 或者 登录接口的用户名或密码不正确";
				break;
			case  102 	:
				errorString=" \t查询中的字段名是大小写敏感的，且必须以英文字母开头，有效的字符仅限在英文字母、数字以及下划线。，或查询对应的字段值不匹配，或提供的地理位置格式不正确\n";
				break;
			case  103 	:
				errorString="  \t查询单个对象或更新对象时必须提供objectId 或 非法的 class 名称，class 名称是大小写敏感的，并且必须以英文字母开头，有效的字符仅限在英文字母、数字以及下划线.\n";
				break;
			case  104 	:
				errorString=" \t关联的class名称不存在";
				break;
			case  105 	:
				errorString=" \t字段名是大小写敏感的，且必须以英文字母开头，有效的字符仅限在英文字母、数字以及下划线 或 字段名是Bmob默认保留的，如objectId,createdAt,updateAt,ACL\n";
				break;
			case  106 	:
				errorString="不是一个正确的指针类型";
				break;
			case  107 	:
				errorString="\t输入格式不正确";
				break;
			case  108  	:
				errorString="用户名和密码是必需的";
				break;
			case  109 	:
				errorString="登录信息是必需的，如邮箱和密码时缺少其中一个提示此信息";
				break;
			case  111 	:
				errorString=" \t传入的字段值与字段类型不匹配，期望是这样(%s)的，但传过来却是这样(%s)的\n";
				break;

			case  112 	:
				errorString="\trequests的值必须是数组";
				break;
			case  113 	:
				errorString="\t\trequests数组中每个元素应该是一个像这样子的json对象";
				break;
			case  114  :
				errorString="\trequests数组大于50";
				break;
			case  117  	:
				errorString="\t\t纬度范围在[-90, 90] 或 经度范围在[-180, 180]";
				break;
			case    120  	:
				errorString="\t要使用此功能，请在Bmob后台应用设置中打开邮箱认证功能开关";
				break;

			case  131  :
				errorString="\t不正确的deviceToken";
				break;
			case  132  :
				errorString="\t\t不正确的installationId";
				break;
			case  133  :
				errorString="\t不正确的deviceType";
				break;
			case  134  :
				errorString="\tdeviceToken已经存在";
				break;
			case  135  :
				errorString="\tinstallationId已经存在";
				break;

			case  136  :
				errorString="\t 只读属性不能修改 或 android设备不需要设置deviceToken";
				break;
			case  138  :
				errorString="\t表是只读的";
				break;
			case  139  :
				errorString="\t角色名称是大小写敏感的，并且必须以英文字母开头，有效的字符仅限在英文字母、数字、空格、横线以及下划线。";
				break;
			case  141  :
				errorString="\t缺失推送需要的data参数";
				break;
			case  140  :
				errorString="\t角色名称已经存在。";
				break;

			case  142  :
				errorString="\t时间格式应该如下： 2013-12-04 00:51:13";
				break;
			case  143  :
				errorString="\t必须是一个数字";
				break;
			case  144  :
				errorString="\t不能是之前的时间";
				break;
			case  145  :
				errorString="\t文件大小错误";
				break;
			case  146  :
				errorString="\t文件名错误";
				break;
			case  147  :
				errorString="\t文件分页上传偏移量错误";
				break;
			case  148  :
				errorString="\t文件上下文错误";
				break;
			case  149  :
				errorString="\t空文件";
				break;
			case  150  :
				errorString="\t文件上传错误";
				break;
			case  151  :
				errorString="\t文件删除错误";
				break;

			case  160  :
				errorString="\t图片错误 ";
				break;
			case  161  :
				errorString="\t图片模式错误";
				break;
			case  162  :
				errorString="\t图片宽度错误";
				break;
			case  163  :
				errorString="\t图片高度错误";
				break;
			case  164  :
				errorString="\t图片长边错误";
				break;
			case  165  :
				errorString="\t图片短边错误";
				break;


			case  201  :
				errorString="\t缺失数据";
				break;
			case  202  :
				errorString="\t用户名已经存在";
				break;
			case  203  :
				errorString="\t邮箱已经存在";
				break;

			case  204  :
				errorString="\t必须提供一个邮箱地址";
				break;
			case  205  :
				errorString="\t没有找到此邮件的用户或没有找到此用户名的用户";
				break;
			case  206  :
				errorString="\t登录用户才能修改自己的信息";
				break;
			case  207  :
				errorString="\t验证码错误";
				break;

			case  208  :
				errorString="\tauthData不正确";
				break;
			case  209  :
				errorString="\t该手机号码已经存在";
				break;

			case  210  :
				errorString="\t旧密码不正确";
				break;

			case 301:
				errorString="\t格式不正确 \t*手机号码或者邮箱格式";
				break;
			case  302  :
				errorString="\t\tBmob后台设置了应用设置值， 如'不允许SDK创建表 '";
				break;

			case  310  :
				errorString="\t云端逻辑运行错误的详细信息";
				break;
			case  311  :
				errorString="\t云端逻辑名称是大小写敏感的，且必须以英文字母开头，有效的字符仅限在英文字母、数字以及下划线";
				break;

			case  401  :
				errorString="\t唯一键不能存在重复的值";
				break;
			case  402  :
				errorString="\t查询的wher语句长度大于具体多少个字节";
				break;

			case  601  :
				errorString="\t不正确的BQL查询语句";
				break;
		}
		return  errorString;
	}

}
