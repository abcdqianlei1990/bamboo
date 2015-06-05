package com.qian.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qian.workeveryday.R;

public class ShowAllWorkDetailItemDialog extends Dialog implements OnItemClickListener{
	private Context context;
	private ListView mLv;
	private String [] mValues = {};
	public ShowAllWorkDetailItemDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	public void initData(){
		mLv = (ListView) findViewById(R.id.lv_showAll_dialog);
		mValues = context.getResources().getStringArray(R.array.showAllDialog);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_all_dialog);
		initData();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.show_all_dialog_item,R.id.tv_showAllDialogItem, mValues);
		mLv.setAdapter(adapter);
		mLv.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//String content = parent.getSelectedItem().toString();
		String content = ((TextView)view).getText().toString().trim();
		if("查看该次出差所有明细".equals(content)){
			//查看本次出差时间内每天的详细情况
		}
		if("为该次出差添加明细".equals(content)){
			Intent intent  = new Intent("addjobcontent");
			context.startActivity(intent);
		}
	}

}
