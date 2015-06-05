package com.qian.activity;


import com.qian.workeveryday.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public final class TestFragment extends Fragment {
	private TextView mTv;
	//private static String mContent;
	//static int  i = 0;
    public static TestFragment newInstance() {
    	//i++;
        TestFragment fragment = new TestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, container, false);
        String content = getArguments().getString("content");
        mTv = (TextView) view.findViewById(R.id.tv_testFragment);
        mTv.setText(content);
        return view;
    }

  
}
