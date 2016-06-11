package com.andyland.firebasedemo.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.common.util.Logger;
import com.google.firebase.crash.FirebaseCrash;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CrashReportFragment extends Fragment {
    private String TAG = CrashReportFragment.class.getSimpleName();
    private View rootView;
    private Activity mActivity;
    @BindView(R.id.btn_generate_crash)
    Button btnGenerateCrash;

    public static CrashReportFragment newInstance(Activity activity) {
        CrashReportFragment crashReportFragment = new CrashReportFragment();
        crashReportFragment.mActivity = activity;
        return crashReportFragment;
    }

    /**
     * To generate ArithmeticException : divide by zero
     * Note : It will take around 20 minutes to display same exception on Firebase crash dashboard.
     * (I have fired this exception on 11:38am and got report at 11:48am.)
     */
    @OnClick(R.id.btn_generate_crash)
    public void generateCrash() {
        // We have added below code to generate ArithmeticException and report this crash on Firebase Crash Reporting.
        int a = 10 / 0;
    }

    private void createCustomLog(String message) {
        FirebaseCrash.log(message);
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
        try {
            if (mActivity != null) {
                mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                uiInitialize(rootView);
            }
        } catch (Exception e) {
            Logger.e(TAG, "Error at onCreateView");
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_crash_report, container, false);
        return rootView;
    }

    private void uiInitialize(View rootView) {
        try {
            if (mActivity != null && rootView != null) {
                ButterKnife.bind(this, rootView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}