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

public class GerenFragment extends Fragment {
	ListView listView;
	List<PhotoInfo> list = new ArrayList<PhotoInfo>();
	ImageView imageview,image_ziliao;	
	SlidingMenu mSlidingMenu;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_geren, container, false);
		listView = (ListView) v.findViewById(R.id.listview_geren);
		mSlidingMenu = (SlidingMenu) getActivity().findViewById(R.id.id_menu);

		imageview = (ImageView) v.findViewById(R.id.image_geren);
		imageview.setOnClickListener(onClick);
		
		image_ziliao = (ImageView) v.findViewById(R.id.ziliao_image);
		image_ziliao.setOnClickListener(onClick);
		getDataDowm();
		return v;
	}

	OnClickListener onClick = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.image_geren:
					mSlidingMenu.toggle();
					break;
				case R.id.ziliao_image:
					Intent intent=new Intent(getActivity(),ZiliaoActivity.class);
					startActivity(intent);
					break;
				default:
					break;
			}
		}
	};

	public void getDataDowm() {
		LayoutInflater inflater = getLayoutInflater(getArguments());
		View headView = inflater.inflate(R.layout.activity_geren_head, null);
		listView.addHeaderView(headView);

		PhotoInfo photoinfo = new PhotoInfo();
		photoinfo.setImageBackground(R.drawable.xiaoyuan);
		photoinfo.setImageHead(R.drawable.head);
		photoinfo.setName("Leo");
		photoinfo.setTime("2天前");
		photoinfo.setText("那年夏天，我们\n最后" + " 一次走过校园的林荫大道， "
				+ " \n走向野心勃勃欲不可知的未来");
		photoinfo.setLastCard(R.drawable.lastactivity);
		list.add(photoinfo);

		PhotoInfo photoinfo1 = new PhotoInfo();
		photoinfo1.setImageBackground(R.drawable.xiaoyuan);
		photoinfo1.setImageHead(R.drawable.head);
		photoinfo1.setName("Leo");
		photoinfo1.setTime("2天前");
		photoinfo1.setText("那年夏天，我们\n最后" + " 一次走过校园的林荫大道， "
				+ " \n走向野心勃勃欲不可知的未来");
		photoinfo1.setLastCard(R.drawable.lastactivity);
		list.add(photoinfo1);
		MyAdapter myAdapter = new MyAdapter(R.layout.activity_attention_two,
				list, getActivity());
		listView.setAdapter(myAdapter);
	}
}
