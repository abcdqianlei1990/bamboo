package com.qian.activity;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import com.qian.DBAction.DayJobAction;
import com.qian.DBAction.TransAction;
import com.qian.DBAction.WorkAction;
import com.qian.entity.DayJobContent;
import com.qian.entity.TransportationInfo;
import com.qian.entity.Work;
import com.qian.workeveryday.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@EActivity(R.layout.display_day_job_detail)
public class DisplayDayJobDetail extends Activity {
	final String TAG = "DisplayDayJobDetail";
	
	@ViewById(R.id.lv_diaplayJobDetail)
	ListView mLv;
	
	@Extra("workInfoID")
	int workInfoID = 0;
	
	private DayJobAction db = null;
	private WorkAction work_db = null;
	private TransAction transportDB = null;
	
	@ViewById(R.id.nullData)
	View mNullDataView;
	
	private List<DayJobContent> mList = new ArrayList<DayJobContent>();
	private String [] items = {"*�鿴���콻ͨʹ�����","*��Ӹ��콻ͨʹ�����","*�����������֧��","*ɾ��������¼"};
	
	/*���н�ͨ��ϸ*/
	private static String [] arr = {};
	/*��Ž�ͨ��ϸid*/
	private static List<Integer> idList = new ArrayList<Integer>();
	private  List<String> transportDetails = new ArrayList<String>();
	private Myadapter adapter = null;
	String date = null;
	
	@AfterViews
	public void initData(){
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_solid_example));
		
		db = new DayJobAction(this);
		work_db = new WorkAction(this);
		adapter = new Myadapter();
		transportDB = new TransAction(this);
		getCursorToList(db.queryByWorkInfoID(workInfoID));
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		clearData();
		initData();
		if(mList.size() == 0){
			mNullDataView.setVisibility(View.VISIBLE);
		}else{
			mLv.setAdapter(adapter);
		}
		
	}
	
	
	public void clearData(){
		mList.clear();
	}
	
	/**
	 * ɾ��һ��ÿ����ϸ�����ѻᷢ���ƻ���Ӧ����work_t
	 */
	public void updateWorkAfterDeleteJob(int workInfoID,double totalCost){
		Cursor c = work_db.queryByID(workInfoID);
		Work work = null;
		while(c.moveToNext()){
			work = new Work(workInfoID, 
					c.getString(c.getColumnIndex("customerName")), 
					c.getString(c.getColumnIndex("location")), 
					c.getDouble(c.getColumnIndex("cost")), 
					c.getString(c.getColumnIndex("startDate")), 
					c.getString(c.getColumnIndex("endDate")), 
					c.getString(c.getColumnIndex("workContent")));
		}
		double cost = work.getCost() - totalCost;
		work.setCost(cost);
		work_db.update(work);
	}
	public void getCursorToList(Cursor cursor){
		if(cursor.getCount() != 0){
			
			while(cursor.moveToNext()){
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			String jobContent = cursor.getString(cursor.getColumnIndex("jobContent"));
			double totalCost = cursor.getDouble(cursor.getColumnIndex("totalCost"));
			double transportCost = cursor.getDouble(cursor.getColumnIndex("transportCost"));
			double otherCost = cursor.getDouble(cursor.getColumnIndex("otherCost"));
			mList.add(new DayJobContent(_id,workInfoID, date, jobContent, totalCost, transportCost,otherCost));
			}
		}
	}
	
	/**
	 * �������ݿ��в�ѯ��cursorת����String����
	 * @param c
	 */
	public  String []  getTransportDetails(Cursor c){
		transportDetails.clear();
		idList.clear();
		while(c.moveToNext()){
			int _id = c.getInt(c.getColumnIndex("_id"));
			int dayJobID = c.getInt(c.getColumnIndex("jobID"));
			String time = c.getString(c.getColumnIndex("time"));
			String type = c.getString(c.getColumnIndex("transportationType"));
			double cost = c.getDouble(c.getColumnIndex("transportationCost"));
			String date = c.getString(c.getColumnIndex("date"));
			TransportationInfo info = new TransportationInfo(_id, dayJobID, date, time, type, cost);
			transportDetails.add(info.toString().trim());
			idList.add(_id);
		}
		String [] s = new String[transportDetails.size()];
		
		transportDetails.toArray(s);
		return s;
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(db != null){
			db.close();
		}
	}

	
	class Myadapter extends BaseAdapter{

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
			View view = convertView;
			Hanlder han = null;
			if(view == null){
				view = LayoutInflater.from(DisplayDayJobDetail.this).inflate(R.layout.display_day_job_detail_item, parent, false);
				han = new Hanlder();
				han.tv = (TextView) view.findViewById(R.id.tv_displayJobDetailItem);
				
				view.setTag(han);
			}
			han = (Hanlder) view.getTag();
			han.tv.setText(mList.get(position).toString());
			return view;
		}
		
	}
	class Hanlder{
		TextView tv;
	}
	
	/**
	 * @param str
	 * @return ��ͨ��ϸʵ��
	 */
	public TransportationInfo getTransportDetailTime(String str){
		TransportationInfo info = null;
		String[] strings = str.split("##");
		info = new TransportationInfo(date, strings[0], strings[1], Double.parseDouble(strings[2]));
		return info;
	}
	
	/**
	 * ��ָ���±��Ӧ�����ݴ��������Ƴ�
	 * 
	 * @param index
	 */
	public static void removeByIndex(int index) {
		if(index >= arr.length){
			System.out.println("error");
		}else{
			String[] temp = arr;
			arr = new String[arr.length - 1];
			int j = 0;
			int i = 0;
			while (i < temp.length) {
				if (i == index && i + 1 < temp.length) {
					i++;
					arr[j++] = temp[i];
					i++;
					continue;
				}
				arr[j++] = temp[i];
				i++;
			}
		}
	}
	
	public void updateWorkInfo(int workInfoID,double cost){
		Cursor c = work_db.queryByID(workInfoID);
		c.moveToFirst();
		Work work = new Work(workInfoID,
				c.getString(c.getColumnIndex("customerName")), 
				c.getString(c.getColumnIndex("location")), 
				c.getDouble(c.getColumnIndex("cost")), 
				c.getString(c.getColumnIndex("startDate")), 
				c.getString(c.getColumnIndex("endDate")), 
				c.getString(c.getColumnIndex("workContent")));
		double total = work.getCost();
		work.setCost(total-cost);
		work_db.update(work);
	}
	
	@ItemClick(R.id.lv_diaplayJobDetail)
	public void itemClick(int position){
		date = mList.get(position).getDate();
		final DayJobContent dayJob = mList.get(position);
		//Log.d("TEST", "##########dayJob:"+dayJob.toString()+"position:"+position);
		final int pos = position;
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setItems(items, new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Cursor cur = transportDB.queryByDate(date);
					if(cur.getCount() == 0){
						Toast.makeText(DisplayDayJobDetail.this, "������Ϣ", Toast.LENGTH_LONG).show();
					}else{
						 AlertDialog.Builder b = new AlertDialog.Builder(DisplayDayJobDetail.this);
						 arr = getTransportDetails(cur);
						b.setItems(arr, new OnClickListener() {
							/*����ÿ�ͨ��ϸ������ɾ��ѡ��*/
							@Override
							public void onClick(DialogInterface dialog, int which) {
								AlertDialog.Builder builder = new AlertDialog.Builder(DisplayDayJobDetail.this);
								//Toast.makeText(DisplayDayJobDetail.this, "################��ǰ�����item��(which):"+which, Toast.LENGTH_LONG).show();
								/*��ȡ�����������*/
								final String content = arr[which];
								final int index = which;
								builder.setItems(new String[]{"ɾ������"}, new OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										TransportationInfo info = getTransportDetailTime(content);
										/*�������ں�ʱ��׼ȥɾ��ָ���Ľ�ͨ��ϸ��¼*/
										//Log.d("TEST", "##########idList.get(index)######"+idList.get(index)+"##############index��"+index);
										transportDB.deleteByID(idList.get(index));
										/*���µ�ǰ����Դarr*/
										//removeByIndex(index);
										idList.remove(index);
										dialog.dismiss();
										/*����job_t���������*/
										double transportCost = dayJob.getTransportCost()-info.getTranspotationCost();
										dayJob.setTransportCost(transportCost);
										dayJob.setTotalCost(transportCost + dayJob.getOtherCost());
										mList.get(pos).setTransportCost(transportCost);
										mList.get(pos).setTotalCost(transportCost + dayJob.getOtherCost());
										
										adapter.notifyDataSetChanged();
										db.updateByDateAndWorkID(dayJob);
										/*����work_t���������*/
										updateWorkInfo(dayJob.getWorkInfoID(),info.getTranspotationCost());
									}
								});
								builder.create().show();
							}
						});
						b.create().show();
						cur = null;
					}
					break;
				case 1:
					Intent addIntent = new Intent("addtransportationdetail");
					addIntent.putExtra("date", date);
					addIntent.putExtra("jobID",mList.get(pos).get_id());
					startActivity(addIntent);
					break;
				case 2:
					Intent Intent2 = new Intent("addothercost");
					Intent2.putExtra("date", date);
					Intent2.putExtra("workInfoID", mList.get(pos).getWorkInfoID());
					startActivity(Intent2);
					Log.d(TAG, "have already jump to addothercost...");
					break;
				case 3:
					AlertDialog.Builder bu = new AlertDialog.Builder(DisplayDayJobDetail.this);
					bu.setTitle("�����Ҫɾô��");
					bu.setPositiveButton("ȷ��ɾ��", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							db.deleteByDate(date);
							transportDB.deleteByJobID(dayJob.get_id());
							updateWorkAfterDeleteJob(mList.get(pos).getWorkInfoID(),mList.get(pos).getTotalCost());
							mList.remove(pos);
							Toast.makeText(DisplayDayJobDetail.this, "ɾ���ɹ�", Toast.LENGTH_LONG).show();
							adapter.notifyDataSetChanged();
						}
					});
					bu.setNegativeButton("�Ը����ҩ", null);
					bu.create().show();
					break;
				default:
					break;
				}
				
			}
		}).create().show();
	}
}
