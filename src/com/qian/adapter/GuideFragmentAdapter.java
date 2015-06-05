package com.qian.adapter;


import java.util.List;

import com.qian.activity.TestFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class GuideFragmentAdapter extends FragmentPagerAdapter{
	List<TestFragment> mList = null;
	
	public GuideFragmentAdapter(FragmentManager fm,List<TestFragment> mList) {
		super(fm);
		this.mList = mList;
	}

	@Override
	public Fragment getItem(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

}
