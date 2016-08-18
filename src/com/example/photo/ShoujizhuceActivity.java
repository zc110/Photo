package com.example.photo;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.json.JSONException;
import org.json.JSONObject;

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
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;


public class ShoujizhuceActivity extends Activity {
	public String phString;
	ImageView imageview;
	Button button_huoquyanzhengma;
	EditText editText_shoujihaoma;
	EditText editText_yanzhengma;
	Button button_zhuce;
	TextView textView_youxiangdenglu;
	private static String APPKEY="1623b6aa80840";
	private static String APPSECRET="4ae449ebbc30bdd8bfe3f770ac52387d";
	private boolean check;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoujizhuce);
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

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.shoujizhuece_huoquyanzhengma:
				/*if (!TextUtils.isEmpty(editText_shoujihaoma.getText().toString())) {
					SMSSDK.getVerificationCode("86", editText_shoujihaoma.getText()
							.toString());
					

					phString = editText_shoujihaoma.getText().toString();
				} else {
					Toast.makeText(ShoujizhuceActivity.this, "电话不能为空", 1)
							.show();
				}*/
				read(editText_shoujihaoma.getText().toString());
				break;
			case R.id.shoujizhece_zhuce:
				//saveuser();
				
				//SMSSDK.submitVerificationCode("86", phString, editText_yanzhengma.getText().toString());
				complete();
				break;
			case R.id.shoujizhuce_youxiangdenglu:
				tiaozhuangdaoyouxiangdenglu();
				break;
			case R.id.shoujizhuce_fanhui:
				shouye();
				break;
			default:
				break;
			}
		}
	};

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
					shouye();
					write(phString);
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
						Toast.makeText(ShoujizhuceActivity.this, des,
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
		editText_shoujihaoma = (EditText) findViewById(R.id.shoujizhuce_shoujihaoma);
		editText_yanzhengma = (EditText) findViewById(R.id.shoujizhece_yanzhengma);
		button_huoquyanzhengma = (Button) findViewById(R.id.shoujizhuece_huoquyanzhengma);
		button_huoquyanzhengma.setOnClickListener(onClickListener);
		button_zhuce = (Button) findViewById(R.id.shoujizhece_zhuce);
		button_zhuce.setOnClickListener(onClickListener);
		textView_youxiangdenglu=(TextView)findViewById(R.id.shoujizhuce_youxiangdenglu);
		textView_youxiangdenglu.setOnClickListener(onClickListener);
		imageview=(ImageView)findViewById(R.id.shoujizhuce_fanhui);
		imageview.setOnClickListener(onClickListener);
	}

public void tiaozhuangdaoyouxiangdenglu(){
	Intent intent=new Intent(ShoujizhuceActivity.this,Activity_mimadenglu.class);
	startActivity(intent);
}
	public void shouye(){
		Intent intent= new Intent(ShoujizhuceActivity.this,
				Activity_jianxing_denglu.class);
		startActivity(intent);
	}
	public void complete() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String httpUrl = "http://192.168.1.151/home/index/shoujizhuce";
				@SuppressWarnings("deprecation")
				HttpPost httpRequest = new HttpPost(httpUrl);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("phonenumb",
						editText_shoujihaoma.getText().toString().trim()));
				params.add(new BasicNameValuePair("yanzhengma",
						editText_yanzhengma.getText().toString().trim()));
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
					Toast.makeText(ShoujizhuceActivity.this, strResult,
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				} else {
					Looper.prepare();
					Toast.makeText(ShoujizhuceActivity.this, "错误",
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				}
			}
		}).start();
		
	}
	String str;
	public void read(final String username) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				StringBuilder builder = new StringBuilder();
				try {
					String httpHost = "http://192.168.1.151/home/index/duqu";
					String name = "username="+username;
					String urlName = httpHost + "?" + name;
					URL url = new URL(urlName);
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					connection.setConnectTimeout(5000);
					connection.setRequestProperty("accept", "*/*");// 设置客户端接受那些类型的信息，通配符代表接收所有类型的数据
					connection.setRequestProperty("connection", "Keep-Alive");// 保持长链接
					connection
							.setRequestProperty("user-agent",
									"Mozilla/4.0(compatible;MSIE 6.0;Windows NT5.1;SV1)");// 设置浏览器代理
					connection
							.setRequestProperty("accept-charset", "utf-8;GBK");// 客户端接受的字符集
					connection.connect();// 建立连接
					InputStream inputStream = connection.getInputStream();
					Map<String, List<String>> headers = connection
							.getHeaderFields();
					for (String key : headers.keySet()) {
						System.out.println(key + "----" + headers.get(key));

					}
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(inputStream));
					String line = bufferedReader.readLine();
					while (line != null && line.length() > 0) {
						builder.append(line);
						line = bufferedReader.readLine();
					}
					bufferedReader.close();
					inputStream.close();
					Log.i("builder-----", builder.toString());
					str= builder.toString();
					phoneHandler.sendEmptyMessage(0);
					} catch (MalformedURLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			
		
		}).start();
	}
	Handler phoneHandler = new Handler() {  
        public void handleMessage(Message msg) {   
        	try {
    			JSONObject jsonObject = new JSONObject(str);
    			int status = jsonObject.getInt("status");
    			String message = jsonObject.getString("message");
    			if(status==2){
    			//prompt.setText("该手机已注册");
    			}else{
    			//prompt.setText(" ");
    			getSMS(editText_shoujihaoma.getText().toString());	
    			}
    			
    		} catch (JSONException e) {
    			// TODO 自动生成的 catch 块
    			e.printStackTrace();
    		}
            
        }   
   };

	
	//服务器写入数据
	public void getSMS(String str){
		if(isMobileNO(str)){
				SMSSDK.getVerificationCode("86",str);   
				//SMSSDK.getVoiceVerifyCode("86",phonEditText.getText().toString());
				phString=str;
			
		}else{
			Toast.makeText(ShoujizhuceActivity.this, "请填写正确的手机号", Toast.LENGTH_LONG).show();
		}
		
	}
	public static boolean isMobileNO(String mobiles) {  
	    /* 
	    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 
	    联通：130、131、132、152、155、156、185、186 
	    电信：133、153、180、189、（1349卫通） 
	    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9 
	    */  
	 String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
	 if (TextUtils.isEmpty(mobiles)) {//如果该参数为空或""
		 return false;  }
	  else {
		  return mobiles.matches(telRegex); 
	  } 
	  }
	public void write(final String username) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				StringBuilder builder = new StringBuilder();
				try {
					String httpHost = "http://192.168.1.151/home/index/shoujizhuce";
					String name = "username="+username;
					String urlName = httpHost + "?" + name;
					URL url = new URL(urlName);
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					connection.setConnectTimeout(5000);
					connection.setRequestProperty("accept", "*/*");// 设置客户端接受那些类型的信息，通配符代表接收所有类型的数据
					connection.setRequestProperty("connection", "Keep-Alive");// 保持长链接
					connection
							.setRequestProperty("user-agent",
									"Mozilla/4.0(compatible;MSIE 6.0;Windows NT5.1;SV1)");// 设置浏览器代理
					connection
							.setRequestProperty("accept-charset", "utf-8;GBK");// 客户端接受的字符集
					connection.connect();// 建立连接
					InputStream inputStream = connection.getInputStream();
					Map<String, List<String>> headers = connection
							.getHeaderFields();
					for (String key : headers.keySet()) {
						System.out.println(key + "----" + headers.get(key));

					}
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(inputStream));
					String line = bufferedReader.readLine();
					while (line != null && line.length() > 0) {
						builder.append(line);
						line = bufferedReader.readLine();
					}
					bufferedReader.close();
					inputStream.close();
					Log.i("builder-----", builder.toString());
					
					
				} catch (MalformedURLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			
		
		}).start();
	}
	
}
