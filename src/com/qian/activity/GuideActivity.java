package com.qian.activity;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.qian.workeveryday.R;
import com.viewpagerindicator.CirclePageIndicator;

@EActivity(R.layout.guide)
public class GuideActivity extends FragmentActivity {

	@ViewById(R.id.vp_guide)
	ViewPager mPager;
	
	@ViewById(R.id.indicator)
	CirclePageIndicator mIndicator;

	private String[] mContent = { "这是第一个引导页面", "这是第二个引导页面", "这是第三个引导页面" };
	private List<TestFragment> mList = new ArrayList<TestFragment>();


	@AfterViews
	public void initData() {
		TestFragment f = null;
		for (int i = 0; i < 3; i++) {
			f = TestFragment.newInstance();
			Bundle b = new Bundle();
			b.putString("content", mContent[i]);
			f.setArguments(b);
			mList.add(f);
		}
		FragmentPagerAdapter adapter = new CirclePageAdapter(
				getSupportFragmentManager());
		mPager.setAdapter(adapter);
		mIndicator.setViewPager(mPager);
		mIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			//
			@Override
			public void onPageSelected(int arg0) {
				/* 如果当前page是最后 */
				if (arg0 + 1 == mList.size()) {
					Button btn = (Button) mList.get(arg0).getView()
							.findViewById(R.id.btn_testFragment);
					btn.setVisibility(View.VISIBLE);
					btn.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent i = new Intent("mainactivity");
							startActivity(i);
							finish();
						}
					});
				}
				Toast.makeText(GuideActivity.this,
						"这是第" + (arg0 + 1) + "个fragment", Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	
	class CirclePageAdapter extends FragmentPagerAdapter {

		public CirclePageAdapter(FragmentManager fm) {
			super(fm);
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

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.finish();
	}

}
