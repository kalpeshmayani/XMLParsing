package com.kpinfotech.adapter;

import java.util.ArrayList;

import com.kpinfotech.global.AppConstant;
import com.kpinfotech.global.AppMethod;
import com.kpinfotech.model.XmlItem;
import com.kpinfotech.xmlparsing.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Adapter_ItemList extends BaseAdapter {
	
	LayoutInflater mInflater;
	Activity activity;

	ArrayList<XmlItem> list;
	
	public Adapter_ItemList(Activity activity, ArrayList<XmlItem> list) {
		mInflater = LayoutInflater.from(activity);
		this.activity = activity;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	public class Holder {
		TextView tvname, tvdescription, tvcost;
		LinearLayout llview;
	}

	public void intialize(View ConvertView, Holder holder) {
		holder.tvname = (TextView) ConvertView.findViewById(R.id.tvname);
		holder.tvdescription = (TextView) ConvertView.findViewById(R.id.tvdescription);
		holder.tvcost = (TextView) ConvertView.findViewById(R.id.tvcost);
		holder.llview = (LinearLayout) ConvertView.findViewById(R.id.llview);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		final Holder holder;
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_itemlist, null);
			holder = new Holder();
			intialize(convertView, holder);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		
		String name = list.get(position).getName();
		holder.tvname.setText(AppMethod.getString(name, AppConstant.DASH));
		
		String description = list.get(position).getDescription();
		holder.tvdescription.setText(AppMethod.getString(description, AppConstant.DASH));
		
		String cost = list.get(position).getCost();
		holder.tvcost.setText(AppMethod.getString(cost, AppConstant.DASH));
		
		OnClickListener onclick = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				
				case R.id.llview:
					break;
					
				default:
					break;
				}
			}
		};
		
		holder.llview.setOnClickListener(onclick);

		return convertView;
	}

}