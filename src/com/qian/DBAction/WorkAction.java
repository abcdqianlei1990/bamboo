package com.qian.DBAction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.qian.dbinfo.DBinfo;
import com.qian.entity.Condition;
import com.qian.entity.Work;
import com.qian.util.MyOpenHelper;

public class WorkAction {
	private MyOpenHelper helper;
	private SQLiteDatabase db;
	private String db_name = DBinfo.DB_NAME;
	private int version = DBinfo.VERSION;
	private String table = "work_t";
	private Context context;
	public WorkAction(Context context){
		this.context = context;
		initDB();
	}
	
	
	public void insert(Work work){
		ContentValues values = new ContentValues();
		values.put("customerName", work.getCustomerName());
		values.put("location", work.getLocation());
		values.put("startDate", work.getStartDate());
		values.put("endDate", work.getEndDate());
		values.put("workContent", work.getWorkContent());
		db.insert(table, null, values);
	}
	/**
	 * 查询所有
	 * @return Cursor
	 */
	public Cursor queryAll(){
		Cursor cursor = null;
		cursor = db.query(table, null, null, null, null, null, null);
		return cursor;
	}
	
	public Cursor queryByID(int id){
		return db.query(table, null, "_id=?", new String[]{id+""}, null, null, null);
	}
	
	public Cursor queryByConditions(Condition con){
		String condition = null;
		String c1 = null;
		String c2 = null;
		String c3 = null;
		if("".equals(con.getCustomerName())){
			c1 = "customerName like '%' and ";
		}else {
			c1 = "customerName like '"+con.getCustomerName()+"' and ";
		}
		if("".equals(con.getStartDate())){
			c2 = "startDate like '%' and ";
		}else{
			c2 = "startDate like '"+con.getStartDate()+"' and ";
		}
		if("".equals(con.getEndDate())){
			c3 = "endDate like '%' ";
		}else{
			c3 = "endDate like '"+con.getEndDate()+"'";
		}
		condition = c1+c2+c3;
		//db.query(table, null, "customerName like '%'", null, null, null, null).getCount()
		return db.query(table, null, condition, null, null, null, null);
	}
	
	public void deleteByWorkID(int id){
		db.delete(table, "_id=?", new String[]{id+""});
	}
	
	/**
	 * 通过id更新数据
	 * @param work
	 */
	public void update(Work work){
		ContentValues values = new ContentValues();
		values.put("customerName", work.getCustomerName());
		values.put("location", work.getLocation());
		values.put("startDate", work.getStartDate());
		values.put("endDate", work.getEndDate());
		values.put("workContent", work.getWorkContent());
		values.put("cost", work.getCost());
		db.update(table, values, "_id=?", new String[]{work.getWorkInfoID()+""});
		//Log.d("TEST", "########update(Work work)"+"将要将出差总花费更新为："+work.getCost());
	}
	/**
	 * 根据输入内容在数据库查找
	 * @param context
	 * @param work
	 * @return Cursor
	 */
	public Cursor queryByCustomerAndStartDate(String customerName,String startDate){
		Cursor cursor = null;
		cursor = db.query(table, null, "customerName=? and startDate=?", new String[]{customerName,startDate}, null, null, null);
		return cursor;
		
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
	
}
