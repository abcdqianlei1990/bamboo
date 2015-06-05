//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.qian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.qian.workeveryday.R.layout;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class DisplayPartWorkInfoActivity_
    extends DisplayPartWorkInfoActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    public final static String END_DATE_EXTRA = "endDate";
    public final static String START_DATE_EXTRA = "startDate";
    public final static String CUSTOMER_NAME_EXTRA = "customerName";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.display_part_workinfo);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        injectExtras_();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static DisplayPartWorkInfoActivity_.IntentBuilder_ intent(Context context) {
        return new DisplayPartWorkInfoActivity_.IntentBuilder_(context);
    }

    public static DisplayPartWorkInfoActivity_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new DisplayPartWorkInfoActivity_.IntentBuilder_(fragment);
    }

    public static DisplayPartWorkInfoActivity_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new DisplayPartWorkInfoActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        mLv = ((ListView) hasViews.findViewById(com.qian.workeveryday.R.id.lv_displayPartWorkInfo));
        mTv = ((TextView) hasViews.findViewById(com.qian.workeveryday.R.id.tv_displayPartWorkInfo_message));
        if (mLv!= null) {
            mLv.setOnItemClickListener(new OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DisplayPartWorkInfoActivity_.this.itemClick(position);
                }

            }
            );
        }
        initData();
    }

    private void injectExtras_() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_!= null) {
            if (extras_.containsKey(END_DATE_EXTRA)) {
                endDate = extras_.getString(END_DATE_EXTRA);
            }
            if (extras_.containsKey(START_DATE_EXTRA)) {
                startDate = extras_.getString(START_DATE_EXTRA);
            }
            if (extras_.containsKey(CUSTOMER_NAME_EXTRA)) {
                customerName = extras_.getString(CUSTOMER_NAME_EXTRA);
            }
            initData();
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        injectExtras_();
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public static class IntentBuilder_
        extends ActivityIntentBuilder<DisplayPartWorkInfoActivity_.IntentBuilder_>
    {

        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            super(context, DisplayPartWorkInfoActivity_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            super(fragment.getActivity(), DisplayPartWorkInfoActivity_.class);
            fragment_ = fragment;
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            super(fragment.getActivity(), DisplayPartWorkInfoActivity_.class);
            fragmentSupport_ = fragment;
        }

        @Override
        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent, requestCode);
            } else {
                if (fragment_!= null) {
                    fragment_.startActivityForResult(intent, requestCode);
                } else {
                    super.startForResult(requestCode);
                }
            }
        }

        public DisplayPartWorkInfoActivity_.IntentBuilder_ endDate(String endDate) {
            return super.extra(END_DATE_EXTRA, endDate);
        }

        public DisplayPartWorkInfoActivity_.IntentBuilder_ startDate(String startDate) {
            return super.extra(START_DATE_EXTRA, startDate);
        }

        public DisplayPartWorkInfoActivity_.IntentBuilder_ customerName(String customerName) {
            return super.extra(CUSTOMER_NAME_EXTRA, customerName);
        }

    }

}
