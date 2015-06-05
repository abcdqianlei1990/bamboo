package com.qian.activity;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qian.DBAction.DayJobAction;
import com.qian.DBAction.WorkAction;
import com.qian.adapter.DisplayAllAdapter;
//import com.qian.entity.DayJobContent;
import com.qian.entity.Work;
import com.qian.workeveryday.R;

@EActivity(R.layout.display_all_workinfo)
public class DisplayAllWorkInfo extends Activity implements OnRefreshListener2<PullToRefreshListView>{
	
	@ViewById(R.id.lv_showAll)
	PullToRefreshListView  mLv;
	
	@ViewById(R.id.displayAllWorkInfoNone)
	TextView mTv;
	
	private WorkAction db = null;
	private DayJobAction job_db = null;
	private Cursor cursor = null;
	private List<Work> list = new ArrayList<Work>();
	private List<Work> list1 = new ArrayList<Work>();
	//private List<DayJobContent> jobContent = new ArrayList<DayJobContent>();
	
	@StringArrayRes(R.array.showAllDialog)
	String [] mValues = {};
	
	private DisplayAllAdapter adapter = null;
	//private Work work = null;
	
	@Override
	protected void onResume() {
		super.onResume();
		initData();
		mLv.setAdapter(adapter);
		mLv.setMode(Mode.BOTH);
		
	}
	
	public void setList(){
		//置空，防止多出数据
		list1.clear();
		list.clear();
		getWorkList();
		list1.addAll(list);
	}
	
	@AfterViews
	public void initData(){
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_solid_example));
		db = new WorkAction(this);
		job_db = new DayJobAction(this);
		//置空，防止多出数据
		cursor = null;
		
		cursor = db.queryAll();
		//mValues = getResources().getStringArray(R.array.showAllDialog);
		setList();
		if(list1.size() == 0){
			mTv.setVisibility(View.VISIBLE);
		}else{
			adapter = new DisplayAllAdapter(list1, this);
		}

	}
	
	public void getWorkList(){
		if(cursor.getCount() != 0){
			while(cursor.moveToNext()){
				String customerName = cursor.getString(cursor.getColumnIndex("customerName"));
				String location = cursor.getString(cursor.getColumnIndex("location"));
				String startDate = cursor.getString(cursor.getColumnIndex("startDate"));
				String endDate = cursor.getString(cursor.getColumnIndex("endDate"));
				String workContent = cursor.getString(cursor.getColumnIndex("workContent"));
				int workInfoID = cursor.getInt(cursor.getColumnIndex("_id"));
				double cost = cursor.getDouble(cursor.getColumnIndex("cost"));
				list.add(new Work(workInfoID,customerName, location, cost, startDate, endDate, workContent));
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(db != null){
			db.close();
		}
	}

	
	@ItemClick(R.id.lv_showAll)
	public void itemClick(int position){

		final int pos = position-1;
		//new ShowAllWorkDetailItemDialog(this);
		//得到该点击项的内容
		//Log.d("TEST", "###########当前点击的项内容是：##########"+list.get(pos).toString());
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setItems(mValues, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Intent Dayintent = new Intent("displaydayjobcontent");
					Dayintent.putExtra("workInfoID",list1.get(pos).getWorkInfoID());
					startActivity(Dayintent);
					break;

				case 1:
					Intent intent = new Intent("adddayjobdetail");
					intent.putExtra("workInfoID",list1.get(pos).getWorkInfoID());
					intent.putExtra("startDate",list1.get(pos).getStartDate());
					intent.putExtra("endDate",list1.get(pos).getEndDate());
					startActivity(intent);
					break;
				case 2:
					AlertDialog.Builder bu = new AlertDialog.Builder(DisplayAllWorkInfo.this);
					bu.setTitle("你真的要删么？");
					bu.setPositiveButton("确认删除",  new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							db.deleteByWorkID(list1.get(pos).getWorkInfoID());
							job_db.deleteByWorkID(list1.get(pos).getWorkInfoID());
							list1.remove(pos);
							adapter.notifyDataSetChanged();
							Toast.makeText(DisplayAllWorkInfo.this, "删除成功", Toast.LENGTH_LONG).show();
						}
					});
					bu.setNegativeButton("再给次机会！", null);
					bu.create().show();
					break;
				default:
					break;
				}
			}
		});
		builder.create().show();
	}
	
	/**
	 * 点击每一个Item时候，得到内容，根据内容查询该内容对应的workInfoID
	 * @param content
	 * @return
	 */
	public int getWorkInfoIDByItemContent(String content){
		int id = 0;
		
		return id;
	}

	@Override
	public void onPullDownToRefresh(
			PullToRefreshBase<PullToRefreshListView> refreshView) {
		Toast.makeText(this, "onPullDownToRefresh...", Toast.LENGTH_LONG).show();
		// new FinishRefreshTask().execute();
		reFreshTask();
	}

	@Override
	public void onPullUpToRefresh(
			PullToRefreshBase<PullToRefreshListView> refreshView) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "onPullUpToRefresh...", Toast.LENGTH_LONG).show();
		//new FinishRefreshTask().execute();
		reFreshTask();
	}
	
	@Background
	public void reFreshTask(){
		mLv.onRefreshComplete();
	}
}
