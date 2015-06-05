package com.qian.DBAction;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qian.dbinfo.DBinfo;
import com.qian.entity.DayJobContent;
import com.qian.util.MyOpenHelper;

public class DayJobAction {

	private MyOpenHelper helper;
	private SQLiteDatabase db;
	private String db_name = DBinfo.DB_NAME;
	private int version = DBinfo.VERSION;
	private String table = "dayJob_t";
	
	Context context;
	
	public DayJobAction(Context context){
		this.context = context;
		initDB();
	}
	
	public void insert(DayJobContent dayJob){
		ContentValues values = new ContentValues();
		values.put("date", dayJob.getDate());
		values.put("jobContent", dayJob.getJobContent());
		values.put("workInfoID", dayJob.getWorkInfoID());
		values.put("totalCost", dayJob.getTotalCost());
		values.put("transportCost", dayJob.getTransportCost());
		values.put("otherCost", dayJob.getOtherCost());
		//Log.d("TEST", "#######insert(DayJobContent dayJob)");
		db.insert(table, null, values);
	}
	
	public Cursor queryAll(){
		return db.query(table, null, null, null, null, null, null);
	}
	
	public Cursor queryByWorkInfoID(int workInfoID){
		return db.query(table, null, "workInfoID=?", new String[]{workInfoID+""}, null, null, null);
	}
	
	/**
	 * 根据日期更新一条数据
	 * @param dayJob
	 */
	public void updateByDateAndWorkID(DayJobContent dayJob){
		ContentValues values = new ContentValues();
		values.put("date", dayJob.getDate());
		values.put("jobContent", dayJob.getJobContent());
		values.put("workInfoID", dayJob.getWorkInfoID());
		values.put("totalCost", dayJob.getTotalCost());
		values.put("transportCost", dayJob.getTransportCost());
		values.put("otherCost", dayJob.getOtherCost());
		db.update(table, values, "date=? and workInfoID=?", new String[]{dayJob.getDate(),dayJob.getWorkInfoID()+""});
	}
	
	public Cursor queryByDate(String date){
		return db.query(table, null, "date=?", new String[]{date}, null, null, null);
	}

	public void deleteByDate(String date){
		db.delete(table, "date=?", new String[]{date});
	}
	public void close(){
		if(db != null){
			db.close();
		}
		if(helper != null){
			helper.close();
		}
	}
	
	public void initDB(){
		helper = new MyOpenHelper(context, db_name, null, version);
		db = helper.getReadableDatabase();
	}

	public void deleteByWorkID(int workInfoID) {
		db.delete(table, "workInfoID=?", new String[]{workInfoID+""});
	}
}
