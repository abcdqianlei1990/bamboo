package com.qian.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qian.DBAction.DayJobAction;
import com.qian.entity.DayJobContent;
import com.qian.workeveryday.R;

@EActivity(R.layout.add_day_job_detail)
public class AddDayJobDetail extends Activity {
	
	@ViewById(R.id.ed_addDayJobDetail_jobContent)
	EditText mInput;
	
	@ViewById(R.id.tv_addDayJobDetail_showTime)
	EditText mDate;
	
	DayJobAction db = null;
	private DayJobContent dayJob = null;
	
	@Extra("startDate")
	String workStartDate;
	
	@Extra("endDate")
	String workEndDate;
	
	//this place,cannot get the same result like the way as workInfoID = getIntent().getIntExtra("workInfoID",-1).
	@Extra("workInfoID")
	int workInfoID = 0;

	@AfterViews
	public void initData(){
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_solid_example));
		db = new DayJobAction(this);
		
		Log.d("TEST", "在添加每日工作明细中，initdata获得开始和截止时间，分别为："+workStartDate+"#"+workEndDate);
	}
	
	
	@Click({R.id.btn_addDayJobDetail_reset,R.id.btn_addDayJobDetail_sub})
	public void clickListener(View v){
		int id = v.getId();
		switch (id) {
		case R.id.btn_addDayJobDetail_reset:
			mDate.setText(null);
			mInput.setText(null);
			break;
		case R.id.btn_addDayJobDetail_sub:
			insert();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 将数据提交到数据库
	 */
	public void insert(){
		String input = mInput.getText().toString();
		String date = mDate.getText().toString();
		if(date.compareTo(workStartDate) < 0){
			Toast.makeText(this, "时间不能小于出差开始日", Toast.LENGTH_LONG).show();
		}else if(date.compareTo(workEndDate) > 0){
			Toast.makeText(this, "时间不能大于出差结束日", Toast.LENGTH_LONG).show();
		}else if(input.equals(" ")){
			Toast.makeText(this, "工作内容不能为空", Toast.LENGTH_LONG).show();
		}else if(isExeist(new DayJobContent(workInfoID, date, null, 0, 0, 0))){
			Toast.makeText(this, "该项已存在，请确认", Toast.LENGTH_LONG).show();
		}
		else{
			int workInfoID = getIntent().getIntExtra("workInfoID", -1);
			dayJob = new DayJobContent(workInfoID, date, input.trim(),0,0,0);
			db.insert(dayJob);
			Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
			this.finish();
		}
	}
	
	public boolean isExeist(DayJobContent job){
		Cursor c = null;
		String date = job.getDate();
		int workInfoID = job.getWorkInfoID();
		c = db.queryByDate(date);
		if(c.getCount() != 0){
			c.moveToFirst();
			int id = c.getInt(c.getColumnIndex("workInfoID"));
			if(id == workInfoID){
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(db != null){
			db.close();
		}
	}
	
}
