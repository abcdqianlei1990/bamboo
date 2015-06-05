package com.qian.custom;

import com.qian.workeveryday.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MyButton extends Button {
	private final String TAG = "MyButton";
	private Context context;
	private MyButton thisBtn;
	public MyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		thisBtn = this;
	}
	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		thisBtn = this;
	}
	public MyButton(Context context) {
		super(context);
		this.context = context;
		thisBtn = this;
	}

	
	
	@Override
	public void setOnTouchListener(OnTouchListener l) {
//		l = new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				int action = event.getAction();
//				Log.i(TAG, "action = "+action);
//				if(action == MotionEvent.ACTION_DOWN){
//					thisBtn.setBackgroundColor(getResources().getColor(R.color.buttonDownColor));
//				}else{
//					thisBtn.setBackgroundColor(getResources().getColor(R.color.labelColor));
//				}
//				return false;
//			}
//		};
	}

	
}
