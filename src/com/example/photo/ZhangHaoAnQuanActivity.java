package com.example.photo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ZhangHaoAnQuanActivity extends Activity {
	ImageView backSheZhi;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhanghaoshezhi);
		turnSheZhi() ;
	}
	public void turnSheZhi() {
		backSheZhi = (ImageView) findViewById(R.id.back_shezhi);
		backSheZhi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}