package com.example.photo;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity{
	protected static final boolean Selector = false;
	LinearLayout layout_geren,layout_guangchang,layout_guanzhu,layout_shezhi;
	ImageView image_geren,image_guangchang,image_guanzhu,image_shezhi;
	TextView text_geren,text_guangchang,text_guanzhu,text_shezhi;
	private SlidingMenu mSlidingMenu;
	MyFragmentAdapter mAdapter;
	ViewPager viewpager;
	ArrayList<Fragment> fragmentList;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenzhongxin);
		getData();
		mAdapter=new MyFragmentAdapter(getSupportFragmentManager(), fragmentList);
		viewpager=(ViewPager)findViewById(R.id.viewpager_view);
		viewpager.setAdapter(mAdapter);
		mSlidingMenu=(SlidingMenu) findViewById(R.id.id_menu);
		
		layout_geren=(LinearLayout)findViewById(R.id.layout_geren);
		image_geren=(ImageView)findViewById(R.id.zhongxin_geren_imageview);
		text_geren=(TextView)findViewById(R.id.zhongxin_geren_textview);
		layout_geren.setOnTouchListener(clickListener);
		image_geren.setOnTouchListener(clickListener);
		text_geren.setOnTouchListener(clickListener);
		
		layout_guangchang=(LinearLayout)findViewById(R.id.layout_guangchang);
		image_guangchang=(ImageView)findViewById(R.id.zhongxin_guangchang_imageview);
		text_guangchang=(TextView)findViewById(R.id.zhongxin_guangchang_textview);
		layout_guangchang.setOnTouchListener(clickListener);
		image_guangchang.setOnTouchListener(clickListener);
		text_guangchang.setOnTouchListener(clickListener);
		
		layout_guanzhu=(LinearLayout)findViewById(R.id.layout_guanzhu);
		image_guanzhu=(ImageView)findViewById(R.id.zhongxin_guanzhu_imageview);
		text_guanzhu=(TextView)findViewById(R.id.zhongxin_guanzhu_textview);
		layout_guanzhu.setOnTouchListener(clickListener);
		image_guanzhu.setOnTouchListener(clickListener);
		text_guanzhu.setOnTouchListener(clickListener);
		
		layout_shezhi=(LinearLayout)findViewById(R.id.layout_shezhi);
		image_shezhi=(ImageView)findViewById(R.id.zhongxin_shezhi_imageview);
		text_shezhi=(TextView)findViewById(R.id.zhongxin_shezhi_textview);
		layout_shezhi.setOnTouchListener(clickListener);
		image_shezhi.setOnTouchListener(clickListener);
		text_shezhi.setOnTouchListener(clickListener);
	}
	private void getData() {
		fragmentList=new ArrayList<Fragment>();
		GerenFragment geren=new GerenFragment();
		fragmentList.add(geren);
		GuangchangFragment guangchang=new GuangchangFragment();
		fragmentList.add(guangchang);
		GuanZhuFragment guanzhu=new GuanZhuFragment();
		fragmentList.add(guanzhu);
		ShezhiFragment shezhi=new ShezhiFragment();
		fragmentList.add(shezhi);
		
	}
	OnTouchListener clickListener=new OnTouchListener() {
		@SuppressLint("ClickableViewAccessibility")
		public boolean onTouch(View v, MotionEvent event) {
			
			switch(v.getId()){
			case R.id.layout_geren:
				viewpager.setCurrentItem(0);
				mSlidingMenu.closeMenu();	
				image_geren.setImageResource(R.drawable.ren);
				text_geren.setTextColor(getResources().getColor(R.color.baise));

				image_guangchang.setImageResource(R.drawable.noguang);
				text_guangchang.setTextColor(getResources().getColor(R.color.heise));
				image_guanzhu.setImageResource(R.drawable.nozhu);
				text_guanzhu.setTextColor(getResources().getColor(R.color.heise));
				image_shezhi.setImageResource(R.drawable.noshe);
				text_shezhi.setTextColor(getResources().getColor(R.color.heise));
				break;		
			case R.id.layout_guangchang:
				viewpager.setCurrentItem(1);
				mSlidingMenu.closeMenu();
				image_guangchang.setImageResource(R.drawable.guang);
				text_guangchang.setTextColor(getResources().getColor(R.color.baise));
				
				image_geren.setImageResource(R.drawable.noren);
				text_geren.setTextColor(getResources().getColor(R.color.heise));
				image_guanzhu.setImageResource(R.drawable.nozhu);
				text_guanzhu.setTextColor(getResources().getColor(R.color.heise));
				image_shezhi.setImageResource(R.drawable.noshe);
				text_shezhi.setTextColor(getResources().getColor(R.color.heise));
				break;
			case R.id.layout_guanzhu:
				viewpager.setCurrentItem(2);
				mSlidingMenu.closeMenu();
				image_guanzhu.setImageResource(R.drawable.zhu);
				text_guanzhu.setTextColor(getResources().getColor(R.color.baise));
				
				image_geren.setImageResource(R.drawable.noren);
				text_geren.setTextColor(getResources().getColor(R.color.heise));
				image_guangchang.setImageResource(R.drawable.noguang);
				text_guangchang.setTextColor(getResources().getColor(R.color.heise));
				image_shezhi.setImageResource(R.drawable.noshe);
				text_shezhi.setTextColor(getResources().getColor(R.color.heise));
				break;
			case R.id.layout_shezhi:
				viewpager.setCurrentItem(3);
				mSlidingMenu.closeMenu();
				image_shezhi.setImageResource(R.drawable.she);
				text_shezhi.setTextColor(getResources().getColor(R.color.baise));
				
				image_geren.setImageResource(R.drawable.noren);
				text_geren.setTextColor(getResources().getColor(R.color.heise));
				image_guanzhu.setImageResource(R.drawable.noguang);
				text_guanzhu.setTextColor(getResources().getColor(R.color.heise));
				image_guangchang.setImageResource(R.drawable.nozhu);
				text_guangchang.setTextColor(getResources().getColor(R.color.heise));
				break;		
			default:
				break;
			
			}return false;
		}
	};
	public void toggleMenu(View view){
		mSlidingMenu.toggle();
	}
	/*OnClickListener clickListener=new OnClickListener() {
		public void onClick(View v) {
			switch(v.getId()){
				case R.id.layout_geren:
					viewpager.setCurrentItem(0);
					mSlidingMenu.toggle();	
					image_geren.setImageResource(R.drawable.ren);
					text_geren.setTextColor(getResources().getColor(R.color.baise));

					image_guangchang.setImageResource(R.drawable.noguang);
					text_guangchang.setTextColor(getResources().getColor(R.color.heise));
					image_guanzhu.setImageResource(R.drawable.nozhu);
					text_guanzhu.setTextColor(getResources().getColor(R.color.heise));
					image_shezhi.setImageResource(R.drawable.noshe);
					text_shezhi.setTextColor(getResources().getColor(R.color.heise));
					break;		
				case R.id.layout_guangchang:
					viewpager.setCurrentItem(1);
					mSlidingMenu.toggle();
					image_guangchang.setImageResource(R.drawable.guang);
					text_guangchang.setTextColor(getResources().getColor(R.color.baise));
					
					image_geren.setImageResource(R.drawable.noren);
					text_geren.setTextColor(getResources().getColor(R.color.heise));
					image_guanzhu.setImageResource(R.drawable.nozhu);
					text_guanzhu.setTextColor(getResources().getColor(R.color.heise));
					image_shezhi.setImageResource(R.drawable.noshe);
					text_shezhi.setTextColor(getResources().getColor(R.color.heise));
					break;
				case R.id.layout_guanzhu:
					viewpager.setCurrentItem(2);
					mSlidingMenu.toggle();
					image_guanzhu.setImageResource(R.drawable.zhu);
					text_guanzhu.setTextColor(getResources().getColor(R.color.baise));
					
					image_geren.setImageResource(R.drawable.noren);
					text_geren.setTextColor(getResources().getColor(R.color.heise));
					image_guangchang.setImageResource(R.drawable.noguang);
					text_guangchang.setTextColor(getResources().getColor(R.color.heise));
					image_shezhi.setImageResource(R.drawable.noshe);
					text_shezhi.setTextColor(getResources().getColor(R.color.heise));
					break;
				case R.id.layout_shezhi:
					viewpager.setCurrentItem(3);
					mSlidingMenu.toggle();
					image_shezhi.setImageResource(R.drawable.she);
					text_shezhi.setTextColor(getResources().getColor(R.color.baise));
					
					image_geren.setImageResource(R.drawable.noren);
					text_geren.setTextColor(getResources().getColor(R.color.heise));
					image_guanzhu.setImageResource(R.drawable.noguang);
					text_guanzhu.setTextColor(getResources().getColor(R.color.heise));
					image_guangchang.setImageResource(R.drawable.nozhu);
					text_guangchang.setTextColor(getResources().getColor(R.color.heise));
					break;		
				default:
					break;
			}		
		}
	};*/
		
}
