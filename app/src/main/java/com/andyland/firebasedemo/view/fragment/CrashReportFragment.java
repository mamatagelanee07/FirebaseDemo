package com.andyland.firebasedemo.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.common.util.Logger;

public class CrashReportFragment extends Fragment {
    private String TAG = CrashReportFragment.class.getSimpleName();
    private View rootView;
    private Activity mActivity;

    public static CrashReportFragment newInstance(Activity activity) {
        CrashReportFragment crashReportFragment = new CrashReportFragment();
        crashReportFragment.mActivity = activity;
        return crashReportFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() != null)
            mActivity = getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mActivity != null) {
            mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            try {
                uiInitialize(rootView);

            } catch (Exception e) {
                Logger.e(TAG, "Error at onCreateView");
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_firebase, container, false);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mActivity != null) {

        }
    }

    private void uiInitialize(View rootView) {
        if (mActivity != null && rootView != null) {

        }
    }
}