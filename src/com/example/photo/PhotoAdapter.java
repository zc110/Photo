package com.example.photo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class PhotoAdapter extends BaseAdapter {
	int ItemId;
	LayoutInflater inflater;
	List<Photo> lists;
	Context context;

	public PhotoAdapter(int itemId, List<Photo> lists, Context context) {

		this.ItemId = itemId;
		inflater = LayoutInflater.from(context);
		this.lists = lists;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(ItemId, null);
		}

		ImageView ImageHead = (ImageView) convertView
				.findViewById(R.id.imageview_head);

		Photo photoinfo = lists.get(position);

		ImageHead.setImageDrawable(context.getResources().getDrawable(
				photoinfo.getImageHead()));

		return convertView;
	}
}
