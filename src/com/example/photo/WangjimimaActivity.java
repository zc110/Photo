package com.example.photo;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;
import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WangjimimaActivity extends Activity {
	Button button_huoquyanzhengma;
	Button button_denglu;
	EditText editText_shoujihaoma;
	EditText editText_yanzhengma;
	EditText editText_chongzhimima;
	EditText editText_quedingmima;
	ImageView imageview_fanhui;
	TextView textview_youxiangdenglu;
	private HashMap<Character, ArrayList<String[]>> first; // 第一层数组
	ArrayList<String[]> second; // 第二层数组
	String countyList = "国家列表：\n";
	private static String APPKEY = "1623846047e00";// "69d6705af33d";0d786a4efe92bfab3d5717b9bc30a10d
	// 填写从短信SDK应用后台注册得到的APPSECRET
	private static String APPSECRET = "358d9c4c209c0a7dec3a70adaeb64c05";
	public String phString;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wangjimima);
		getId();
		SMSSDK.initSDK(this, APPKEY, APPSECRET, false);
		EventHandler eh = new EventHandler() {

			@Override
			public void afterEvent(int event, int result, Object data) {
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				mHandler.sendMessage(msg);
			}

		};
		SMSSDK.registerEventHandler(eh);
	}
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int event = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			Log.e("event", "event=" + event);
			// System.out.println("--------result---0"+event+"--------*"+result+"--------"+data);

			if (result == SMSSDK.RESULT_COMPLETE) {
				if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
					Toast.makeText(getApplicationContext(), "发送验证码成功",
							Toast.LENGTH_SHORT).show();
				} else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
					denglu();
					Toast.makeText(getApplicationContext(), "验证成功",
							Toast.LENGTH_SHORT).show();
				} 
			} else {
				int status = 0;
				try {
					((Throwable) data).printStackTrace();
					Throwable throwable = (Throwable) data;

					JSONObject object = new JSONObject(throwable.getMessage());
					String des = object.optString("detail");
					status = object.optInt("status");
					if (!TextUtils.isEmpty(des)) {
						Toast.makeText(WangjimimaActivity.this, des,
								Toast.LENGTH_SHORT).show();
						return;
					}
				} catch (Exception e) {
					SMSLog.getInstance().w(e);
				}
			}
		};
	};
	protected void onDestroy() {
		super.onDestroy();
		SMSSDK.unregisterAllEventHandler();
	};
	public void getId() {
		// 找到id
		button_huoquyanzhengma = (Button) findViewById(R.id.wangjimima_huoquyanzhengma);
		button_denglu = (Button) findViewById(R.id.wangjimima_denglu);
		editText_shoujihaoma = (EditText) findViewById(R.id.wangjimima_shoujihaoma);
		editText_yanzhengma = (EditText) findViewById(R.id.wangjimima_yanzhengma);
		editText_chongzhimima = (EditText) findViewById(R.id.wangjimima_chongzhimima);
		editText_quedingmima = (EditText) findViewById(R.id.wangjimima_quedingmima);
		imageview_fanhui=(ImageView)findViewById(R.id.wangjimima_fanhui);
		textview_youxiangdenglu=(TextView)findViewById(R.id.wangjimima_youxiangdenglu);
		// 点击事件
		button_huoquyanzhengma.setOnClickListener(onclick);
		button_denglu.setOnClickListener(onclick);
		editText_shoujihaoma.setOnClickListener(onclick);
		editText_yanzhengma.setOnClickListener(onclick);
		editText_chongzhimima.setOnClickListener(onclick);
		editText_quedingmima.setOnClickListener(onclick);
		imageview_fanhui.setOnClickListener(onclick);
		textview_youxiangdenglu.setOnClickListener(onclick);
	}

	OnClickListener onclick = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.wangjimima_huoquyanzhengma:
				if (!TextUtils.isEmpty(editText_shoujihaoma.getText().toString())) {
					SMSSDK.getVerificationCode("86", editText_shoujihaoma.getText()
							.toString());
					// SMSSDK.getVoiceVerifyCode("86",phonEditText.getText().toString());

					phString = editText_shoujihaoma.getText().toString();
				} else {
					Toast.makeText(WangjimimaActivity.this, "电话不能为空", 1)
							.show();
				}	
				huoquyanzhengma();
				break;
			case R.id.wangjimima_denglu:
				denglu();
				break;
			case R.id.wangjimima_fanhui
			:
				fanhuishouye();
				break;
			case R.id.wangjimima_youxiangdenglu:
				youxiangdenglu();
				break;
			default:
				break;
			}
		}
	};

public void huoquyanzhengma(){
	String str_shoujihaoma = editText_shoujihaoma.getText().toString();
	String str_yanzhengma = editText_yanzhengma.getText().toString();
	
	if (str_shoujihaoma.equals("")) {
		Toast.makeText(WangjimimaActivity.this, "手机号码不能为空",
				Toast.LENGTH_SHORT).show();
		return;
	} else if (str_shoujihaoma.length() != 11) {
		Toast.makeText(WangjimimaActivity.this, "请输入正确的手机号码",
				Toast.LENGTH_SHORT).show();
		return;
	}
	/*else if(str_yanzhengma.equals("")){
		Toast.makeText(WangjimimaActivity.this, "验证码不能为空",
				Toast.LENGTH_SHORT).show();
		return;
		}*/
}

public void youxiangdenglu(){
	Intent intent=new Intent(WangjimimaActivity.this,Activity_mimadenglu.class);
	startActivity(intent);
}
public void fanhuishouye(){
	Intent intent=new Intent(WangjimimaActivity.this,Activity_jianxing_denglu.class);
	startActivity(intent);
}
public void dengluchengong(){
	Intent intent=new Intent(WangjimimaActivity.this,MainActivity.class);
	startActivity(intent);
	complete();
}

	public boolean denglu() {
		String str_shoujihaoma = editText_shoujihaoma.getText().toString();
		String str_yanzhengma = editText_yanzhengma.getText().toString();
	
		String str_diyicimima = editText_chongzhimima.getText().toString();
		String str_diercimima = editText_quedingmima.getText().toString();
		if (str_shoujihaoma.equals("")) {
			Toast.makeText(WangjimimaActivity.this, "手机号码不能为空",
					Toast.LENGTH_SHORT).show();
		} else if (str_shoujihaoma.length() != 11) {
			Toast.makeText(WangjimimaActivity.this, "请输入正确的手机号码",
					Toast.LENGTH_SHORT).show();
		}else if(str_yanzhengma.equals("")){
			Toast.makeText(WangjimimaActivity.this, "验证码不能为空",
					Toast.LENGTH_SHORT).show();
		} else if (str_diyicimima.equals("") || str_diercimima.equals("")) {
			Toast.makeText(WangjimimaActivity.this, "密码不能为空",
					Toast.LENGTH_SHORT).show();
		} else if (str_diyicimima.equals(str_diercimima)) {
			Toast.makeText(WangjimimaActivity.this, "登录成功", Toast.LENGTH_SHORT)
					.show();
			dengluchengong();
			return true;
		} else {
			Toast.makeText(WangjimimaActivity.this, "密码输入不一样，请重新输入",
					Toast.LENGTH_SHORT).show();
		}
		return true;
	}
	public void tiaozhuanyouxiangdenglu(){
		Intent intent=new Intent(WangjimimaActivity.this,Activity_mimadenglu.class);
		startActivity(intent);
	}
	protected void complete() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String httpUrl = "http://192.168.1.151/home/index/wangjimima";
				@SuppressWarnings("deprecation")
				HttpPost httpRequest = new HttpPost(httpUrl);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("phonenumb",
						editText_shoujihaoma.getText().toString().trim()));
				params.add(new BasicNameValuePair("yanzhengma",
						editText_yanzhengma.getText().toString().trim()));
				params.add(new BasicNameValuePair("password",
						editText_chongzhimima.getText().toString().trim()));
				HttpEntity httpentity = null;
				try {
					httpentity = new UrlEncodedFormEntity(params, "utf8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				httpRequest.setEntity(httpentity);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient.execute(httpRequest);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					String strResult = null;
					try {
						strResult = EntityUtils.toString(httpResponse
								.getEntity());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Looper.prepare();
					Toast.makeText(WangjimimaActivity.this, strResult,
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				} else {
					Looper.prepare();
					Toast.makeText(WangjimimaActivity.this, "错误",
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				}
			}
		}).start();
		
	}

}
