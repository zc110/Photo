package com.example.photo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ZiliaoActivity extends Activity{
	private static final int RESULT_LOAD_IMAGE = 0;
	ImageView view_fanhui,view_touxiang,tiaozhuan;
	TextView view_qianming;
	Spinner spinner;	
	ArrayAdapter<String> adapter;
	List<String>  list=new ArrayList<String>();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenzhiliao);
		view_fanhui=(ImageView)findViewById(R.id.fanhui_geren);
		view_fanhui.setOnClickListener(l);
		
		view_touxiang=(ImageView)findViewById(R.id.touxiang_imageview);
		view_touxiang.setOnClickListener(l);
		
		tiaozhuan=(ImageView)findViewById(R.id.tiaozhuan);
		tiaozhuan.setOnClickListener(l);
		
		view_qianming=(TextView)findViewById(R.id.qianming_text);
		view_qianming.setOnClickListener(l);
		String str=getIntent().getStringExtra("qianming");
		view_qianming.setText(str);
		spinner=(Spinner)findViewById(R.id.spinner_ziliao);
		adapter=new ArrayAdapter<String>(ZiliaoActivity.this,
				android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		list.add("男");
		list.add("女");
		spinner.setAdapter(adapter);	
		spinner.setOnItemSelectedListener(itemSelectedListener);
	}
	OnClickListener l=new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()){
				case R.id.fanhui_geren:
					finish();
					break;
				case R.id.touxiang_imageview:
					getContent();
					break;
				case R.id.tiaozhuan:
					Intent intent=new Intent(ZiliaoActivity.this,QianmingActivity.class);
					startActivity(intent);
					break;
				default:
					break;
			}
		}
	};
	OnItemSelectedListener itemSelectedListener=new OnItemSelectedListener(){
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			
		}
		public void onNothingSelected(AdapterView<?> parent) {
			
		}
	};
	public void getContent(){
		Intent intent=new Intent(Intent.ACTION_PICK, 
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		/*intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("vnd.android.cursor.item/photo");
		startActivity(intent);*/
		startActivityForResult(intent, RESULT_LOAD_IMAGE);
	}
}
