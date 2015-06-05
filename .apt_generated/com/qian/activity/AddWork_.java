//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.qian.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import com.qian.workeveryday.R.id;
import com.qian.workeveryday.R.layout;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class AddWork_
    extends AddWork
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.add_work);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
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

    public static AddWork_.IntentBuilder_ intent(Context context) {
        return new AddWork_.IntentBuilder_(context);
    }

    public static AddWork_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new AddWork_.IntentBuilder_(fragment);
    }

    public static AddWork_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new AddWork_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        mEndDate = ((EditText) hasViews.findViewById(id.add_endDate));
        mSubmit = ((Button) hasViews.findViewById(id.add_btn_submit));
        mContent = ((EditText) hasViews.findViewById(id.add_workContent));
        mReset = ((Button) hasViews.findViewById(id.add_btn_reset));
        mStartDate = ((EditText) hasViews.findViewById(id.add_startDate));
        mLocation = ((EditText) hasViews.findViewById(id.add_location));
        mName = ((EditText) hasViews.findViewById(id.add_customerName));
        if (mReset!= null) {
            mReset.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    AddWork_.this.clickEvent(view);
                }

            }
            );
        }
        if (mSubmit!= null) {
            mSubmit.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    AddWork_.this.clickEvent(view);
                }

            }
            );
        }
        if (mName!= null) {
            mName.setOnFocusChangeListener(new OnFocusChangeListener() {


                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    AddWork_.this.focusChangeListener(view, hasFocus);
                }

            }
            );
        }
        if (mLocation!= null) {
            mLocation.setOnFocusChangeListener(new OnFocusChangeListener() {


                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    AddWork_.this.focusChangeListener(view, hasFocus);
                }

            }
            );
        }
        if (mStartDate!= null) {
            mStartDate.setOnFocusChangeListener(new OnFocusChangeListener() {


                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    AddWork_.this.focusChangeListener(view, hasFocus);
                }

            }
            );
        }
        if (mEndDate!= null) {
            mEndDate.setOnFocusChangeListener(new OnFocusChangeListener() {


                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    AddWork_.this.focusChangeListener(view, hasFocus);
                }

            }
            );
        }
        if (mContent!= null) {
            mContent.setOnFocusChangeListener(new OnFocusChangeListener() {


                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    AddWork_.this.focusChangeListener(view, hasFocus);
                }

            }
            );
        }
        initData();
    }

    public static class IntentBuilder_
        extends ActivityIntentBuilder<AddWork_.IntentBuilder_>
    {

        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            super(context, AddWork_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            super(fragment.getActivity(), AddWork_.class);
            fragment_ = fragment;
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            super(fragment.getActivity(), AddWork_.class);
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

    }

}
