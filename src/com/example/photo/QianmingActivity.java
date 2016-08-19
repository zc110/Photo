package com.example.photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class QianmingActivity extends Activity{
	TextView text_wancheng;
	ImageView image_fanhui;
	EditText edittext;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qianming);
		text_wancheng=(TextView)findViewById(R.id.text_wancheng);
		text_wancheng.setOnClickListener(l);
		image_fanhui=(ImageView)findViewById(R.id.fanhui_ziliao);
		image_fanhui.setOnClickListener(l);
		
		edittext=(EditText)findViewById(R.id.edittext);
	}
	OnClickListener l=new OnClickListener() {
		public void onClick(View v) {
			switch(v.getId()){
			  	case R.id.fanhui_ziliao:
			  		finish();
			  		break;
			  	case R.id.text_wancheng:
			  		getData();
			  		break;
			  	default:
			  		break;
			}	
		}
	};
	
	public void getData(){
		 Intent intent=new Intent();
		 intent.putExtra("qianming",edittext.getText().toString());
		 setResult(RESULT_OK, intent);
		 
		 finish();
	}
}
