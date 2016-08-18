package com.example.photo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GerenFragment extends Fragment {
	ListView listView;
	List<Photo> list = new ArrayList<Photo>();
	ImageView imageview;
	ImageView imageview2;
	SlidingMenu mSlidingMenu;
	TextView geduo;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_geren, container, false);
		listView = (ListView) v.findViewById(R.id.listview_geren);
		mSlidingMenu = (SlidingMenu) getActivity().findViewById(R.id.id_menu);

		View mainView = inflater.inflate(R.layout.activity_gerenzhongxin, null,
				false);
		mSlidingMenu = (SlidingMenu) mainView.findViewById(R.id.id_menu);

		imageview2 = (ImageView) v.findViewById(R.id.photomain_imageview2);
		imageview2.setOnClickListener(onClick);

		imageview = (ImageView) v.findViewById(R.id.photomain_imageview);
		imageview.setOnClickListener(onClick);

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
				Intent intent = new Intent(getActivity(), ZiliaoActivity.class);
				startActivity(intent);
				break;
			default:
				break;

			case R.id.photomain_imageview:
				mSlidingMenu.openMenu();
				break;
			case R.id.photomain_imageview2:
				intent = new Intent(getActivity(), ZiliaoActivity.class);
				startActivity(intent);
				break;

			}
		}
	};

	public void getDataDowm() {

		LayoutInflater inflater = getLayoutInflater(getArguments());
		View headView = inflater.inflate(R.layout.photo_main_head, null);
		listView.addHeaderView(headView);
		geduo = (TextView) headView.findViewById(R.id.geduo);

		LayoutInflater inflater1 = getLayoutInflater(getArguments());
		View view2 = inflater1.inflate(R.layout.activity_gerenzhiliao, null);
		//TextView DengDaiGeGuo = (TextView) view2
			//	.findViewById(R.id.waitingGeduo);
		//String GeDuo = (String) DengDaiGeGuo.getText();
	//Log.i("GeDuo", "" + GeDuo);

	//	geduo.setText(GeDuo);
		for (int i = 0; i < 3; i++) {
			Photo photoinfo = new Photo();

			//photoinfo.setImageBackground(R.drawable.xiaoyuan);
			photoinfo.setImageHead(R.drawable.head);
			photoinfo.setName("Leo");
			photoinfo.setTime("2天前");
			photoinfo.setText("那年夏天，我们\n最后" + " 一次走过校园的林荫大道， "
					+ " \n走向野心勃勃欲不可知的未来");
			photoinfo.setLastCard(R.drawable.lastactivity);
			list.add(photoinfo);

			photoinfo.setImageHead(R.drawable.head);

			list.add(photoinfo);
		}
		PhotoAdapter myAdapter = new PhotoAdapter(R.layout.photo_main_down,
				list, getActivity());
		listView.setAdapter(myAdapter);
		// 通过list长度设置卡片张数
		Object[] numb = list.toArray();// 返回列表所有元素
		int num = numb.length;
		String str = String.valueOf(num);
		TextView textView = (TextView) headView.findViewById(R.id.textview_one);
		textView.setText(str);
	}

	private LayoutInflater getSystemService(String layoutInflaterService) {
		// TODO Auto-generated method stub
		return null;
	}

}
