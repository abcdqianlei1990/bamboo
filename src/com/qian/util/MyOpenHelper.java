package com.qian.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyOpenHelper extends SQLiteOpenHelper {

	String workSQL = "create table work_t(_id integer primary key autoincrement,customerName text,location text,startDate text,endDate text,workContent text,cost double);";
	String dayJobContentSQL = "create table dayJob_t(_id integer primary key autoincrement,workInfoID integer,date text,jobContent text,totalCost double,transportCost double,otherCost double);";
	String transSQL = "create table transport_t(_id integer primary key autoincrement,jobID integer,date text,time text,transportationType text,transportationCost double);";
	private Context context;

	public MyOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(dayJobContentSQL);
		db.execSQL(transSQL);
		db.execSQL(workSQL);
		Log.d("TEST", "3张表初始化完毕......");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
