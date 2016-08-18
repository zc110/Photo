package com.example.photo;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class XuanmobanActivity extends Activity{
	ImageView image_moban;
	Gallery gallery;
	GalleryAdapter adapter;
	ImageView imageview;
	int[] str={R.drawable.tupian,R.drawable.tupian,R.drawable.tupian};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xuanmoban);
		imageview=(ImageView)findViewById(R.id.imageview_moban);
		imageview.setOnClickListener(clickListener);
		
		image_moban=(ImageView)findViewById(R.id.moban);
		image_moban.setOnClickListener(clickListener);
		gallery=(Gallery)findViewById(R.id.gallery);
		adapter=new GalleryAdapter(XuanmobanActivity.this,str);
		gallery.setAdapter(adapter);
	}
	OnClickListener clickListener=new OnClickListener(){
		public void onClick(View v) {
			switch (v.getId()){
				case R.id.imageview_moban:
					finish();
					break;
				case R.id.moban:
					image_moban.setImageResource(R.drawable.huigou);
					break;
				default:
					break;
			}
			
		}
	};
	public class GalleryAdapter extends BaseAdapter{
		Context context;
		int [] image;
		public GalleryAdapter(){
		}
		public GalleryAdapter(Context context,int[] images){
			this.context=context;
			this.image=images;
		}
		public int getCount() {
			return image.length;
		}
		public Object getItem(int position) {
			return image[position];
		}
		public long getItemId(int position) {
		
			return position;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageview=new ImageView(context);
			LayoutParams params= new LayoutParams(500,500);
			imageview.setLayoutParams(params);
			Drawable drawable=context.getResources().getDrawable(image[position]);
			drawable.setBounds(0,0,400,500);
			imageview.setImageDrawable(drawable);
			return imageview;
		}
		
	}
}
