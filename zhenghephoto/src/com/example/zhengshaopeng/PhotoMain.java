package com.example.zhengshaopeng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zc.photo.R;
import com.example.zhaoliusheng.ZhongxinActivity;

public class PhotoMain extends Fragment {// 个人timeLine
	ImageView imageview;
	ImageView imageview2;
	TextView textview;

	ListView listView;
	List<Map<String, Object>> lists_dowm = new ArrayList<Map<String, Object>>();

	String[] keysDown = { "head_down" };
	int[] viewIdDown = { R.id.imageview_head };
	MyAdapter myAdapter_head;
	MyAdapter myAdapter_dowm;
	private LinearLayout layout;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = (LinearLayout) inflater.inflate(R.layout.photo_main,
				container, false);

		return layout;
	}

	public void init() {

		listView = (ListView) layout.findViewById(R.id.listView_dowm);
		getDataDowm();

		myAdapter_dowm = new MyAdapter(getActivity(), R.layout.photo_main_down,
				lists_dowm, viewIdDown, keysDown);
		listView.setAdapter(myAdapter_dowm);
		//
		LayoutInflater lif = getLayoutInflater(getArguments());// 视图转换器
		View headerView = lif.inflate(R.layout.photo_main_head, null);// 用视图转换器把layout转换成view
		listView.addHeaderView(headerView);// 添加到listview当中去
		// 点击跳转个人中心
		imageview = (ImageView) layout.findViewById(R.id.photomain_imageview);
		imageview.setOnClickListener(onClick);

	}

	OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.photomain_imageview:
				Intent intent = new Intent(getActivity(),
						ZhongxinActivity.class);
				startActivity(intent);
				break;

			}
		}

	};

	public void getDataDowm() {
		for (int i = 0; i < 6; i++) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("head_down", R.drawable.head);
			lists_dowm.add(map);

		}
	}

}
