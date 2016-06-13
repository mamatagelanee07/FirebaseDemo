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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;

public class FeedbackFragment extends Fragment {
    private String TAG = FeedbackFragment.class.getSimpleName();
    private View rootView;
    private Activity mActivity;

    public static FeedbackFragment newInstance(Activity activity) {
        FeedbackFragment crashReportFragment = new FeedbackFragment();
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
        rootView = inflater.inflate(R.layout.fragment_feedback_database, container, false);
        return rootView;
    }

    private void uiInitialize(View rootView) {
        try {
            if (mActivity != null && rootView != null) {
                ButterKnife.bind(this, rootView);

                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}