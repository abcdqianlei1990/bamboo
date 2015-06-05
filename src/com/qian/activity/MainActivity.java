package com.qian.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.qian.workeveryday.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements OnKeyListener{
	
	//private String TAG = "MainActivity";

	@ViewById(R.id.main_inputName)
	EditText mInputName;
	
	@ViewById(R.id.main_inputStart)
	EditText mInputStart;
	
	@ViewById(R.id.main_inputEnd)
	EditText mInputEnd;
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		//findView();
//		//initData();
////		FragmentManager fm = getFragmentManager();
////		fm .findFragmentById(R.layout.test_fragment).getView().findViewById(R.id.tv_testFragment);
////		FragmentTransaction transaction = fm.beginTransaction();
////		transaction.add(new TestFragment(), "111");
////		transaction.commit();
//		
//		
//	}

	
	@Click({R.id.main_QueryAllBtn,R.id.main_addBtn,R.id.main_searchBtn})
	public void click(View btn){
		int id = btn.getId();
		switch(id){
		case R.id.main_QueryAllBtn:
			Intent intent = new Intent("displayall");
			startActivity(intent);
			break;

		case R.id.main_addBtn:
			Intent addIntent = new Intent("addworkinfo");
			startActivity(addIntent);
			break;
		case R.id.main_searchBtn:
			String customerName = mInputName.getText().toString();
			String startDate = mInputStart.getText().toString();
			String endDate = mInputEnd.getText().toString();
			
			if(inputNull(customerName) && inputNull(startDate) && inputNull(endDate)){
				Toast.makeText(this, "查询条件不能都为空", Toast.LENGTH_LONG).show();
			}else{
				Intent sIntent = new Intent("queryworkinfo");
				sIntent.putExtra("customerName", customerName);
				sIntent.putExtra("startDate", startDate);
				sIntent.putExtra("endDate", endDate);
				startActivity(sIntent);
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * when the application is started,change the value of first_start to false
	 */
	public void changeFirstStartValue(){
		SharedPreferences sp = getSharedPreferences("share_pre", 0);
		sp.edit().putBoolean("first_start", false).commit();
	}
	
	@AfterViews
	public void initData(){
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_solid_example));
		changeFirstStartValue();
	}
	
	public boolean inputNull(String str){
		if("".equals(str)){
			return true;
		}
		return false;
	}
	
	
	//键盘敲击事件,屏蔽返回键
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if(event.equals(KeyEvent.ACTION_DOWN)){
			if(keyCode == KeyEvent.KEYCODE_BACK){
				return true;
			}
		}
		return false;
	}
}
