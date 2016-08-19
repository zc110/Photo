package com.example.photo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.EventLog.Event;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photo.R;


public class YouxiangzhuceActivity extends Activity {
	Button button;
	EditText editText_youxiangzhanghao;
	EditText editText_shezhimima;
	TextView youxiang_shoujizhuce;
	DatabaseHelper databaseHelper;
	SQLiteDatabase sqliteDatabase;
	ImageView imageview_fanhui;
	StringBuilder builder = new StringBuilder();
	

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youxiangzhuce);
		getId();
		// StrictMode.ThreadPolicy policy = new
		// StrictMode.ThreadPolicy.Builder().permitAll().build();
	
		
		// StrictMode.setThreadPolicy(policy);
	}

	protected void complete() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String httpUrl = "http://192.168.1.151/home/index/youxiangzhuce";
				@SuppressWarnings("deprecation")
				HttpPost httpRequest = new HttpPost(httpUrl);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username",
						editText_youxiangzhanghao.getText().toString().trim()));
				params.add(new BasicNameValuePair("password",
						editText_shezhimima.getText().toString().trim()));
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
					Toast.makeText(YouxiangzhuceActivity.this, strResult,
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				} else {
					Looper.prepare();
					Toast.makeText(YouxiangzhuceActivity.this, "错误",
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				}
			}
		}).start();
		
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.youxiang_zhuce:
				huoquyanzhengma();
				// baocun(username, password);

				break;
			case R.id.shoujizhuce:
				tiaozhuan();
				break;
			case R.id.youxiangzhuce_fanhui:
				shouye();
				break;
			default:
				break;
			}
		}

	};

	@SuppressLint("NewApi")
	public void huoquyanzhengma() {
		String youxiang = editText_youxiangzhanghao.getText().toString().trim();
		String mima = editText_shezhimima.getText().toString().trim();

		if (youxiang.equals("")) {

			Toast.makeText(YouxiangzhuceActivity.this, "账号不能为空",
					Toast.LENGTH_SHORT).show();
			return;
		} else if (mima.equals("")) {
			Toast.makeText(YouxiangzhuceActivity.this, "密码不能为空",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(YouxiangzhuceActivity.this, "注册成功",
					Toast.LENGTH_SHORT).show();
			complete();
			shouye();
			return;
		}

	}
	public void getId() {
		button = (Button) findViewById(R.id.youxiang_zhuce);
		button.setOnClickListener(onClickListener);
		editText_youxiangzhanghao = (EditText) findViewById(R.id.youxiang_zhanghao);
		editText_shezhimima = (EditText) findViewById(R.id.youxiang_shezhimima);
		youxiang_shoujizhuce = (TextView) findViewById(R.id.shoujizhuce);
		youxiang_shoujizhuce.setOnClickListener(onClickListener);
		imageview_fanhui = (ImageView) findViewById(R.id.youxiangzhuce_fanhui);
		imageview_fanhui.setOnClickListener(onClickListener);
		this.getDir("user", Context.MODE_PRIVATE);
	}
	public void tiaozhuan() {
		Intent intent = new Intent(YouxiangzhuceActivity.this,
				ShoujizhuceActivity.class);
		startActivity(intent);

	}
	public void shouye() {
		Intent intent = new Intent(YouxiangzhuceActivity.this,
				Activity_jianxing_denglu.class);
		startActivity(intent);
	}

	

}
