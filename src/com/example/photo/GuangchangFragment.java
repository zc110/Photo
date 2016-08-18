package com.example.photo;

import java.util.ArrayList;
import java.util.List;




import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GuangchangFragment extends Fragment{
	ListView listview;
	ImageView imageview,image_fanhui;
	SlidingMenu mSlidingMenu;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.square,container, false);
		listview=(ListView)v.findViewById(R.id.sequare_listview);
		image_fanhui=(ImageView)v.findViewById(R.id.sequare_imageview);
		image_fanhui.setOnClickListener(l);
		
		
		
		imageview=(ImageView)v.findViewById(R.id.sequare_guangchangTwo);
		imageview.setOnClickListener(l);
		
		View mainView=inflater.inflate(R.layout.activity_gerenzhongxin,null,false);
		mSlidingMenu=(SlidingMenu) mainView.findViewById(R.id.id_menu);
		getData();
		return v;
	}
	private void getData() {
		List<Photo> list = new ArrayList<Photo>();
		
		LayoutInflater inflater = getLayoutInflater(getArguments());
		View headView = inflater.inflate(R.layout.sequare_head, null);
		listview.addHeaderView(headView);
		
		for(int i=0;i<3;i++){
		Photo photo = new Photo();
	
		photo.setImageHead(R.drawable.head);
		photo.setName("Leo");
		photo.setTime("2天前");
		photo.setText("那年夏天，我们");
		photo.setZuihou("最后一次走过校园的林荫大道");
		photo.setLastCard(R.drawable.sequare_one);
		photo.setFuture("走过野心勃勃不可未知的未来");
		list.add(photo);
	
}

		SequenceAdapter myAdapter = new SequenceAdapter(R.layout.sequare_down,
				list, getActivity());
		listview.setAdapter(myAdapter);
		
	}
	public void toggleMenu(View view) {
		mSlidingMenu.toggle();
	}
	
	OnClickListener l=new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()){
				case R.id.sequare_imageview:
					mSlidingMenu.toggle();
					break;
				default:
					break;
			}	
		}
	};
	
}