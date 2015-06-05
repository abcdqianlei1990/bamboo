package com.qian.activity;



import org.androidannotations.annotations.EActivity;

import com.qian.workeveryday.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

@EActivity(R.layout.luanch_app)
public class LuanchActivity extends Activity {

	private static final int LUANCH_EXEIST_TIME = 3000;
	private boolean mFirst = false;
	
	LuanchActivity mThis;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.luanch_app);
		
		mThis = this;
		/*
		 * 测试用：设置每次启动都是第一次启动app
		 */
		SharedPreferences sp = getSharedPreferences("share_pre", 0);
		sp.edit().putBoolean("first_start", true).commit();
		
		isFirstStart();
		/*
		 * if the application is first time to start then luanch the guide
		 * activity else luanch the mainActivity
		 */
		if (mFirst) {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					//Intent mainIntent = new Intent("guideactivity");
					Intent mainIntent = new Intent(mThis,GuideActivity_.class);
					//Intent mainIntent = new Intent(LuanchActivity.this, "guideactivity");
					mThis.startActivity(mainIntent);
					mThis.finish();
					
					
					
//					Intent mainIntent = new Intent(LuanchActivity.this,
//							MainActivity.class);
//					LuanchActivity.this.startActivity(mainIntent);
//					LuanchActivity.this.finish();
				}
			}, LUANCH_EXEIST_TIME);
		} else {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					Intent mainIntent = new Intent(mThis,
							MainActivity_.class);
					mThis.startActivity(mainIntent);
					mThis.finish();
				}
			}, LUANCH_EXEIST_TIME);
		}

	}

	public boolean isFirstStart() {
		SharedPreferences sp = getSharedPreferences("share_pre", 0);
		mFirst = sp.getBoolean("first_start", false);
		return mFirst;
		
	}

}
