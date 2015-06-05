package com.qian.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qian.DBAction.DayJobAction;
import com.qian.DBAction.TransAction;
import com.qian.DBAction.WorkAction;
import com.qian.entity.DayJobContent;
import com.qian.entity.TransportationInfo;
import com.qian.entity.Work;
import com.qian.util.Tools;
import com.qian.workeveryday.R;

@EActivity(R.layout.add_trans)
public class AddTransportDetail extends Activity implements 
		OnItemSelectedListener{
	
	//private static final String TAG = "AddTransportDetail";
	
	@ViewById(R.id.tv_addTrans_showDate)
	TextView mDate;
	
	@ViewById(R.id.ed_addTrans_time)
	EditText mTime;
	
	@ViewById(R.id.spinner_addTrans_type)
	Spinner mSpinner;
	
	@ViewById(R.id.ed_addTrans_cost)
	EditText mCost;
	
	@StringArrayRes(R.array.transportationType)
	String[] mTypeValues = {};
	
	@Extra("date")
	String date;
	private TransAction transport_db;
	private DayJobAction job_db;
	private WorkAction work_db;

	private String selectedType = null;
	private double inputCost = 0;
	
	@Extra("jobID")
	int jobID = 0;
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (transport_db != null) {
			transport_db.close();
		}
		if (job_db != null) {
			job_db.close();
		}
		if (work_db != null) {
			work_db.close();
		}
	}

//	@TextChange(R.id.ed_addTrans_time)
//	public void textChangeListener(){
//		
//	}

	@AfterViews
	public void initData() {
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_solid_example));
		
		transport_db = new TransAction(this);
		work_db = new WorkAction(this);
		job_db = new DayJobAction(this);
		mSpinner.setAdapter(new ArrayAdapter<String>(this,
				R.layout.trans_type_item, R.id.tv_typeItem, mTypeValues));
		mDate.setText("���ڣ�" + date);
		mSpinner.setOnItemSelectedListener(this);
	}

	@Click(R.id.btn_addTrans_ok)
	public void clickListener(){
		// Log.d("TEST",
					// "#####################��ӽ�ͨ��ϸȷ�ϼ�����##################");
		if (inputCheck()) {
			insert();
			Toast.makeText(this, "��ӳɹ�", Toast.LENGTH_LONG).show();
			update();
			this.finish();
		}
	}


	public void insert() {
		TransportationInfo trans = new TransportationInfo();
		trans.setDate(date);
		String time = Tools.timeFormatChange(mTime.getText().toString().trim());
		trans.setTime(time);
		inputCost = Double.parseDouble(mCost.getText().toString().trim());
		trans.setTranspotationCost(inputCost);
		trans.setTranspotationType(selectedType);
		trans.setDayJobID(jobID);
		transport_db.insert(trans);

	}

	/**
	 * ��ͨ����ͨ��ϸ�����ڲ�ѯÿ�չ�����ϸ�����¸ñ�Ļ�������
	 * Ȼ�����ÿ����ϸ�е�workID��ѯwork_t�����¸ñ���
	 */
	public void update() {
		// update work_t dayJob_t
		// ����һ����ͨ���ݣ�������2�����cost
		Log.d("TEST", "#######�޸ĵ������ǣ�"+date);
		Cursor job_cur = job_db.queryByDate(date);
		int workInfoID = 0;
		Cursor work_cur = null;
		DayJobContent job = null;
		Work work = null;
		while(job_cur.moveToNext()){
			workInfoID = job_cur.getInt(job_cur.getColumnIndex("workInfoID"));
			String jobContent = job_cur.getString(job_cur.getColumnIndex("jobContent"));
			double totalCost = job_cur.getDouble(job_cur.getColumnIndex("totalCost"));
			double transportCost = job_cur.getDouble(job_cur.getColumnIndex("transportCost"));
			double otherCost = job_cur.getDouble(job_cur.getColumnIndex("otherCost"));
			
			//update cost
			transportCost += inputCost;
			totalCost = transportCost + otherCost;
			
			job = new DayJobContent(workInfoID, date, jobContent, totalCost, transportCost, otherCost);
			Log.d("TEST", "#######�޸�ÿ����ϸ�ǣ�"+job.toString());
		}
		work_cur = work_db.queryByID(workInfoID);
		Log.d("TEST", "#######�޸ĵ�workInfoID�ǣ�"+workInfoID);
		while(work_cur.moveToNext()){
			String customerName = work_cur.getString(work_cur.getColumnIndex("customerName"));
			String location = work_cur.getString(work_cur.getColumnIndex("location"));
			String startDate = work_cur.getString(work_cur.getColumnIndex("startDate"));
			String endDate = work_cur.getString(work_cur.getColumnIndex("endDate"));
			String workContent = work_cur.getString(work_cur.getColumnIndex("workContent"));
			double cost = work_cur.getDouble(work_cur.getColumnIndex("cost"));
			
			//update cost
			cost += inputCost;
			
			work = new Work(workInfoID, customerName, location, cost, startDate, endDate, workContent);
			Log.d("TEST", "#######�޸ĳ�����ϸ�ǣ�"+work.toString()+"�޸ĳ���cost��"+cost);
		}
		job_db.updateByDateAndWorkID(job);
		work_db.update(work);
	}

	public boolean inputCheck() {
		String time = mTime.getText().toString();
		String cost = mCost.getText().toString();
		if (selectedType == null) {
			Toast.makeText(this, "��ѡ��ͨ��ʽ", Toast.LENGTH_LONG).show();
		} else if ("".equals(time) || "".equals(cost)) {
			Toast.makeText(this, "����д���ʲô��", Toast.LENGTH_LONG).show();
		} else if (!Tools.timeIsFormat(time)) {
			Toast.makeText(this, "ʱ���ʽ��������", Toast.LENGTH_LONG).show();
		} else {
			return true;
		}
		return false;
	}
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		selectedType = mTypeValues[position];
		//Toast.makeText(this, selectedType + "��ѡ��", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}


	
	
}
