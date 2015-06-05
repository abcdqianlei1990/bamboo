package com.qian.DBAction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qian.dbinfo.DBinfo;
import com.qian.entity.TransportationInfo;
import com.qian.util.MyOpenHelper;

public class TransAction {

	private MyOpenHelper helper;
	private SQLiteDatabase db;
	private String db_name = DBinfo.DB_NAME;
	private int version = DBinfo.VERSION;
	private String table = "transport_t";
	private Context context;
	
	public TransAction(Context context){
		this.context = context;
		initDB();
	}
	
	public void insert(TransportationInfo trans){
		ContentValues values = new ContentValues();
		values.put("jobID", trans.getDayJobID());
		values.put("date", trans.getDate());
		values.put("time", trans.getTime());
		values.put("transportationType", trans.getTranspotationType());
		values.put("transportationCost", trans.getTranspotationCost());
		db.insert(table, null, values);
	}
	
	public Cursor queryAll(){
		return db.query(table, null, null, null, null, null, null);
	}
	
	public Cursor queryByDate(String date){
		return db.query(table, null, "date=?", new String[]{date}, null, null, null);
	}
	
	public Cursor queryByDateAndTime(String date,String time){
		return db.query(table, null, "date=? and time=?", new String[]{date,time}, null, null, null);
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

//	public void deleteByDate(String date) {
//		db.delete(table, "date=?", new String[]{date});
//	}
	public void deleteByID(int _id) {
		db.delete(table, "_id=?", new String[]{_id+""});
	}
	public void deleteByJobID(int  jobID) {
		db.delete(table, "jobID=?", new String[]{jobID+""});
	}
	
}
