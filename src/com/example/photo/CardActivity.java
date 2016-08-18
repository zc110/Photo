package com.example.photo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CardActivity extends Activity{
	ImageButton  buttonBack;
	ImageView image_leo;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_card);
		buttonBack=(ImageButton)findViewById(R.id.card_back);
		buttonBack.setOnClickListener(clickListener);
		
		image_leo=(ImageView)findViewById(R.id.image_leo);
		image_leo.setOnClickListener(clickListener);
	}
	OnClickListener clickListener=new OnClickListener(){
		public void onClick(View v) {
			switch (v.getId()){
				case R.id.card_back:
					finish();
				case R.id.image_leo:
					image_leo.setImageResource(R.drawable.huigou);
					break;
				default:
					break;
			}
			
		}
	};
}
