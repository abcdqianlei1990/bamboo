package com.qian.adapter;

import java.util.List;

import android.content.Context;
import android.content.UriMatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qian.entity.Work;
import com.qian.hanlder.DisplayAllWorkInfoHandler;
import com.qian.workeveryday.R;

public class DisplayAllAdapter extends BaseAdapter {

	private List<Work> list = null;
	private Context context;
	
	public DisplayAllAdapter(List<Work> list,Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		DisplayAllWorkInfoHandler handler = null;
		if(view == null){
			view = LayoutInflater.from(context).inflate(R.layout.display_workinfo_item, parent, false);
			handler = new DisplayAllWorkInfoHandler();
			handler.showDetail = (TextView) view.findViewById(R.id.tv_showOneWorkinfo);
			view.setTag(handler);
		}
		handler = (DisplayAllWorkInfoHandler) view.getTag();
		handler.showDetail.setText(list.get(position).toString());
		return view;
	}



}	
