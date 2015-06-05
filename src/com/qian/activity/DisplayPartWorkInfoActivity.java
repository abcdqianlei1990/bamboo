package com.qian.activity;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qian.DBAction.DayJobAction;
import com.qian.DBAction.WorkAction;
import com.qian.entity.Condition;
import com.qian.entity.Work;
import com.qian.workeveryday.R;

@EActivity(R.layout.display_part_workinfo)
public class DisplayPartWorkInfoActivity extends Activity {
	
	@ViewById(R.id.lv_displayPartWorkInfo)
	ListView mLv;
	
	@ViewById(R.id.tv_displayPartWorkInfo_message)
	TextView mTv;

	private WorkAction db = null;
	private DayJobAction job_db = null;
	private List<Work> mList = new ArrayList<Work>();
	
	@Extra("customerName")
	String customerName;
	
	@Extra("startDate")
	String startDate;
	
	@Extra("endDate")
	String endDate;

	private MyAdapter adapter;

	/* 单击item，弹出的选项 */
	private String[] mItems = {};

	@AfterViews
	@AfterExtras
	public void initData() {
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_solid_example));
		db = new WorkAction(this);
		job_db = new DayJobAction(this);
		adapter = new MyAdapter();
		mLv.setAdapter(adapter);
		query();
		mItems = getResources().getStringArray(R.array.showAllDialog);
	}

	public void query() {
		Condition con = new Condition(customerName, startDate, endDate);
		Cursor c = db.queryByConditions(con);
		if (c.getCount() == 0) {
			mLv.setVisibility(View.GONE);
			mTv.setVisibility(View.VISIBLE);
		} else {
			if (c.getCount() == 1) {
				c.moveToFirst();
				mList.add(new Work(c.getInt(c.getColumnIndex("_id")), c
						.getString(c.getColumnIndex("customerName")), c
						.getString(c.getColumnIndex("location")), c.getDouble(c
						.getColumnIndex("cost")), c.getString(c
						.getColumnIndex("startDate")), c.getString(c
						.getColumnIndex("endDate")), c.getString(c
						.getColumnIndex("workContent"))));
			} else {
				while (c.moveToNext()) {
					mList.add(new Work(c.getInt(c.getColumnIndex("_id")), c
							.getString(c.getColumnIndex("customerName")), c
							.getString(c.getColumnIndex("location")), c
							.getDouble(c.getColumnIndex("cost")), c.getString(c
							.getColumnIndex("startDate")), c.getString(c
							.getColumnIndex("endDate")), c.getString(c
							.getColumnIndex("workContent"))));
				}
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (db != null) {
			db.close();
		}
		if(job_db != null){
			job_db.close();
		}
	}

	class Holder {
		TextView tv;
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mList.size();
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
			Holder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(
						DisplayPartWorkInfoActivity.this).inflate(
						R.layout.display_part_workinfo_item, parent, false);
				holder = new Holder();
				holder.tv = (TextView) convertView
						.findViewById(R.id.tv_displayPartWorkInfoItem);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.tv.setText(mList.get(position).toString());
			return convertView;
		}

	}

	@ItemClick(R.id.lv_displayPartWorkInfo)
	public void itemClick(int position){
		final int pos = position;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		/* 单击操作选项监听 */
		builder.setItems(mItems, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Intent Dayintent = new Intent("displaydayjobcontent");
					Dayintent.putExtra("workInfoID",mList.get(pos).getWorkInfoID());
					startActivity(Dayintent);
					break;
				case 1:
					Intent intent = new Intent("adddayjobdetail");
					intent.putExtra("workInfoID",mList.get(pos).getWorkInfoID());
					intent.putExtra("startDate",mList.get(pos).getStartDate());
					intent.putExtra("endDate",mList.get(pos).getEndDate());
					startActivity(intent);
					break;
				case 2:
					AlertDialog.Builder bu = new AlertDialog.Builder(DisplayPartWorkInfoActivity.this);
					bu.setTitle("确认删除该项么？");
					bu.setPositiveButton("确认",new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							db.deleteByWorkID(mList.get(pos).getWorkInfoID());
							job_db.deleteByWorkID(mList.get(pos).getWorkInfoID());
							mList.remove(pos);
							adapter.notifyDataSetChanged();
							Toast.makeText(DisplayPartWorkInfoActivity.this, "删除成功", Toast.LENGTH_LONG).show();
						}
					});
					bu.setNegativeButton("手抖了", null);
					bu.create().show();
					break;
				default:
					break;
				}
			}
		});
		builder.create().show();
	}

}
