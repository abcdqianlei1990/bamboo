package com.qian.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qian.DBAction.DayJobAction;
import com.qian.DBAction.WorkAction;
import com.qian.entity.DayJobContent;
import com.qian.entity.Work;
import com.qian.workeveryday.R;

/**
 * 每天工作明细添加其他支出
 * @author Qian
 * @time   2014-8-25下午3:45:09
 */

@EActivity(R.layout.add_other_cost)
public class AddOtherCostActivity extends Activity {
	
	@ViewById(R.id.ed_addOtherCost_content)
	EditText mContent;
	
	@ViewById(R.id.ed_addOtherCost_money)
	EditText mMoney;
	
	private DayJobAction db;
	private WorkAction work_db;
	
	@Extra("date")
	String date = null;
	
	@Extra("workInfoID")
	int workInfoID = 0;
	
	private DayJobContent dayJob;
	private String content = null;
	private String money = null;
	
	@AfterViews
	public void initData(){
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_solid_example));
		db = new DayJobAction(this);
		work_db = new WorkAction(this);
		Cursor cur = db.queryByDate(date);
		while(cur.moveToNext()){
			dayJob = new DayJobContent(cur.getInt(cur.getColumnIndex("workInfoID")), 
					cur.getString(cur.getColumnIndex("date")), 
					cur.getString(cur.getColumnIndex("jobContent")), 
					cur.getDouble(cur.getColumnIndex("totalCost")), 
					cur.getDouble(cur.getColumnIndex("transportCost")), 
					cur.getDouble(cur.getColumnIndex("otherCost")));
		}
	}
	public void getInput(){
		content = mContent.getText().toString();
		money = mMoney.getText().toString();
	}

	@Click({R.id.btn_addOtherCost_reset,R.id.btn_addOtherCost_ok})
	public void clickListener(View v){
		int id = v.getId();
		switch (id) {
		case R.id.btn_addOtherCost_reset:
			resetInput();
			break;
		case R.id.btn_addOtherCost_ok:
			getInput();
			if(inputCheckOK()){
				double otherCost = dayJob.getOtherCost();
				double totalCost = dayJob.getTotalCost();
				otherCost += Double.parseDouble(money);
				totalCost += Double.parseDouble(money);
				dayJob.setOtherCost(otherCost);
				dayJob.setTotalCost(totalCost);
				db.updateByDateAndWorkID(dayJob);
				updateWorkInfo();
				
				Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
				this.finish();
			}
			break;
		default:
			break;
		}
	}

	
	public void resetInput(){
		mContent.setText(null);
		mMoney.setText(null);
	}
	
	public boolean inputCheckOK(){
		if(" ".equals(content) || " ".equals(money)){
			Toast.makeText(this, "有输入为空", Toast.LENGTH_LONG).show();
		}else if(!moneyCheckOK(money)){
			Toast.makeText(this, "金额输入有误", Toast.LENGTH_LONG).show();
		}else{
			return true;
		}
		return false;
	}
	
	public boolean moneyCheckOK(String money){
		char[] array = money.toCharArray();
		if(array.length == 0){
			return false;
		}else if(array[0] == '-'){
			return false;
		}
		if(!isNumber(money)){
			return false;
		}
		return true;
	}
	public boolean isNumber(String money){
		String patten = "^[0-9]+$";
		if(money.matches(patten)){
			return true;
		}
		return false;
	}
	
	public void updateWorkInfo(){
		Cursor c = work_db.queryByID(workInfoID);
		Work work = null;
		double cost = 0;
		while(c.moveToNext()){
			
			work = new Work(workInfoID, 
					c.getString(c.getColumnIndex("customerName")), 
					c.getString(c.getColumnIndex("location")), 
					c.getDouble(c.getColumnIndex("cost")), 
					c.getString(c.getColumnIndex("startDate")), 
					c.getString(c.getColumnIndex("endDate")), 
					c.getString(c.getColumnIndex("workContent")));
			
		}
		cost = work.getCost();
		
		cost += Double.parseDouble(money);
		work.setCost(cost);
		
		work_db.update(work);
		
	}
}
