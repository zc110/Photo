package com.example.photo;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Activity_jianxing_denglu extends Activity{
	
	RelativeLayout relativelayout_jianxingzhuce,relativelayout_jianxingdenglu;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jianxing_denglu_layout);
		shouyedenglu();
	}
	public void shouyedenglu(){
		relativelayout_jianxingzhuce=(RelativeLayout) findViewById(R.id.relativelayout_jianxingzhuce);
		relativelayout_jianxingdenglu=(RelativeLayout) findViewById(R.id.relativelayout_jianxingdenglu);
		
		relativelayout_jianxingzhuce.setOnClickListener(clickListener);
		
		relativelayout_jianxingdenglu.setOnClickListener(clickListener);
	}
	OnClickListener clickListener=new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.relativelayout_jianxingdenglu:
				shouye_denglu_tianzhuan();
				break;
			case R.id.relativelayout_jianxingzhuce:
				zhuce();
				break;
			default:
				break;
			}
		}
	};
	public void shouye_denglu_tianzhuan(){
		Intent intent=new Intent(this,Activity_shoujidenglu.class);
		startActivity(intent);
	}
	public void zhuce(){
		Intent intent=new Intent(this,YouxiangzhuceActivity.class);
		startActivity(intent);
	}
}
