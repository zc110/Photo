package com.example.photo;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SequenceAdapter extends BaseAdapter {
	int ItemId;
	LayoutInflater inflater;
	List<Photo> lists;
	Context context;

	public SequenceAdapter(int itemId, List<Photo> lists, Context context) {

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

		ViewHolder holder;// fragment里找到listview的控件Id进行监听
		if (convertView == null) {
			convertView = inflater.inflate(ItemId, null);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.sequare_rightarrow);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, GuangchangActivity.class);
				context.startActivity(intent);
			}

		});

		ImageView ImageHead = (ImageView) convertView
				.findViewById(R.id.imageView_sequare);
		TextView leo = (TextView) convertView.findViewById(R.id.leo);
		TextView twoday = (TextView) convertView.findViewById(R.id.twoday);
		TextView summer = (TextView) convertView.findViewById(R.id.summer);
		TextView zuihou = (TextView) convertView.findViewById(R.id.zuihou);
		ImageView sequare_rightarrow = (ImageView) convertView
				.findViewById(R.id.sequare_rightarrow);
		TextView future = (TextView) convertView.findViewById(R.id.future);

		Photo photoinfo = lists.get(position);

		ImageHead.setImageDrawable(context.getResources().getDrawable(
				photoinfo.getImageHead()));
		leo.setText(photoinfo.getName());
		twoday.setText(photoinfo.getTime());
		summer.setText(photoinfo.getText());
		zuihou.setText(photoinfo.getZuihou());
		sequare_rightarrow.setImageDrawable(context.getResources().getDrawable(
				photoinfo.getLastCard()));
		future.setText(photoinfo.getFuture());
		return convertView;
	}

	// 成员内部类
	final class ViewHolder {
		public ImageView image;

	}
}
