package com.qian.activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qian.DBAction.WorkAction;
import com.qian.entity.Work;
import com.qian.util.Tools;
import com.qian.workeveryday.R;

/**
 * add work
 * 
 * @author Qian
 * @time 2014-8-19����2:02:46
 */

@EActivity(R.layout.add_work)
public class AddWork extends Activity{
	
	@ViewById(R.id.add_btn_reset)
	Button mReset;
	
	@ViewById(R.id.add_btn_submit)
	Button mSubmit;
	
	@ViewById(R.id.add_customerName)
	EditText mName;
	
	@ViewById(R.id.add_location)
	EditText mLocation;
	
	@ViewById(R.id.add_startDate)
	EditText mStartDate;
	
	@ViewById(R.id.add_endDate)
	EditText mEndDate;
	
	@ViewById(R.id.add_workContent)
	EditText mContent;
	
	private WorkAction db;
	//private boolean canClick = false;	//submit control flag
	//private boolean inputOK = false;

	@AfterViews
	public void initData(){
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_solid_example));
		db = new WorkAction(this);
	
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(db != null){
			db.close();
		}
	}
	
	@Click({R.id.add_btn_reset,R.id.add_btn_submit})
	public void clickEvent(View btn){

		int id = btn.getId();
		switch (id) {
		case R.id.add_btn_reset:
			resetInput();
			break;
		case R.id.add_btn_submit:
			/*���ж����������*/
			//�������ж�
			if(haveEmptyInput()){
				Toast.makeText(this, "�����п�����", Toast.LENGTH_LONG).show();
			}
			//�ж����������Ƿ���ȷ
			else if(!Tools.formatCheck(mStartDate.getText().toString().trim())){
				Toast.makeText(this, "��ʼʱ���ʽ���벻��ȷ", Toast.LENGTH_LONG).show();
			}
			else if(!Tools.formatCheck(mEndDate.getText().toString().trim())){
				Toast.makeText(this, "����ʱ���ʽ���벻��ȷ", Toast.LENGTH_LONG).show();
			}
			//�ж��Ƿ��Ѵ���
			else if(isExeist(mName.getText().toString().trim(),mStartDate.getText().toString().trim())){
				Toast.makeText(this, "�����Ѵ��ڣ���ȷ��", Toast.LENGTH_LONG).show();
			}else{
				db.insert(new Work(mName.getText().toString().trim(), mLocation.getText().toString().trim(), 0, mStartDate.getText().toString().trim(), 
						mEndDate.getText().toString().trim(), mContent.getText().toString().trim()));
				//resetInput();
				this.finish();
				Toast.makeText(this, "��ӳɹ�", Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}
	}
	
	@FocusChange({R.id.add_customerName,R.id.add_location,R.id.add_startDate,R.id.add_endDate,R.id.add_workContent})
	public void focusChangeListener(View v,boolean hasFocus){
		if((v == mName) && (!hasFocus)){
			if(mName.getText() == null){
				Toast.makeText(this, "�ͻ�������Ϊ��", Toast.LENGTH_LONG).show();
			}
		}
		if((v == mLocation)&&(!hasFocus)){
			if(mLocation.getText() == null){
				Toast.makeText(this, "����ز���Ϊ��", Toast.LENGTH_LONG).show();
			}
		}
		if((v == mStartDate)&&(!hasFocus)){
			if(mStartDate.getText() == null){
				Toast.makeText(this, "��ʼ���ڲ���Ϊ��", Toast.LENGTH_LONG).show();
			}
		}
		if((v == mEndDate)&&(!hasFocus)){
			if(mEndDate.getText() == null){
				Toast.makeText(this, "�������ڲ���Ϊ��", Toast.LENGTH_LONG).show();
			}
		}
		if((v == mContent)&&(!hasFocus)){
			if(mContent.getText() == null){
				Toast.makeText(this, "��������ݲ���Ϊ��", Toast.LENGTH_LONG).show();
			}
		}
	}
	public void resetInput(){
		mName.setText(null);
		mLocation.setText(null);
		mStartDate.setText(null);
		mEndDate.setText(null);
		mContent.setText(null);
	}

	/**
	 * jugde if have empty input
	 * @return true:have   false:no
	 */
	public boolean haveEmptyInput(){
		Log.d("TEST", "########haveEmptyInput#######");
		if((TextUtils.isEmpty(mName.getText().toString().trim())) || (TextUtils.isEmpty(mLocation.getText().toString().trim())) || (TextUtils.isEmpty(mStartDate.getText().toString().trim()))
				|| (TextUtils.isEmpty(mEndDate.getText().toString().trim())) || (TextUtils.isEmpty(mContent.getText().toString().trim()))){
			return true;
		}
		return false;
	}
	
	public boolean isExeist(String customerName,String startDate){
		Cursor cursor = db.queryByCustomerAndStartDate(customerName,startDate);
		if(cursor.getCount() == 0){
			return false;
		}
		return true;
	}
	
}
