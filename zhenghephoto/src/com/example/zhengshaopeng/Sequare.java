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
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.zc.photo.R;
import com.example.zhaoliusheng.ZhongxinActivity;

public class Sequare extends Fragment {// 广场1
	ImageView imageview;
	ImageView imageview2;
	ToggleButton toggle;
	Switch switcher;
	MyAdapter myAdapter;
	private ListView listView;
	List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
	String[] keys = { "image_head", "leo", "twoday", "summer", "final",
			"future" };
	int[] viewId = { R.id.imageView_sequare, R.id.leo, R.id.twoday,
			R.id.summer, R.id.zuihou, R.id.future };
	private LinearLayout layout;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = (LinearLayout) inflater.inflate(R.layout.square, container,
				false);
		
		init();

		return layout;
	}

	public void init() {
		listView = (ListView) layout.findViewById(R.id.sequare_listview);
		getData();//添加list里添加数据
		myAdapter = new MyAdapter(getActivity(), R.layout.sequare_down, lists,
				viewId, keys);
		listView.setAdapter(myAdapter);
		LayoutInflater Inflater = getLayoutInflater(getArguments());// 视图转换器
		View headerView = Inflater.inflate(R.layout.sequare_head, null);// 用视图转换器把layout转换成view
		listView.addHeaderView(headerView);// 添加到listview当中去
		// ////////////////////////////////
		imageview = (ImageView) layout.findViewById(R.id.sequare_imageview);

		imageview.setOnClickListener(onClick);

	}

	OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			// 广场Square跳转广场SequareTwo
			case R.id.sequare_imageview:
				Intent intent = new Intent(getActivity(),
						ZhongxinActivity.class);// 广场到ZhongxinActivity
				startActivity(intent);
				break;

			}
		}

	};

	public void getData() {
		for (int i = 0; i < 3; i++) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("image_head", R.drawable.head);
			map.put("leo", "Leo");
			map.put("twoday", "2天前");
			map.put("summer", "那年夏天，我们");
			map.put("final", "最后一次走过校园的林荫大道");

			map.put("future", "走过野心勃勃不可未知的未来");
			lists.add(map);
		}
	}

}
