package com.andyland.firebasedemo.common.util;


import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.view.activity.FireBaseActivity;
import com.andyland.firebasedemo.view.fragment.CrashReportFragment;

/**
 * Created by Andy on 10/16/2015.
 */
public class FragmentLoader {

    private static final String TAG = FragmentLoader.class.getSimpleName();
    private FragmentManager fragmentManager;
    private String currentFragment;
    private static FragmentLoader fragmentLoader;

    public static FragmentLoader newInstance(FireBaseActivity coreActivity) {
        if (fragmentLoader == null)
            fragmentLoader = new FragmentLoader(coreActivity);
        return fragmentLoader;
    }

    FragmentLoader(FireBaseActivity coreActivity) {
        this.fragmentManager = coreActivity.getSupportFragmentManager();
    }

    public String getCurrentFragment() {
        return currentFragment;
    }

    public void loadCrashReportFragment(Activity activity) {
        try {

            boolean fragmentPopped = fragmentManager.popBackStackImmediate(Constants.TAG_FRAGMENT_CRASH_REPORT, 0);
            boolean isAddable = !fragmentPopped && fragmentManager.findFragmentByTag(Constants.TAG_FRAGMENT_CRASH_REPORT) == null;
            if (!isAddable) {
                fragmentManager.popBackStack(Constants.TAG_FRAGMENT_CRASH_REPORT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CrashReportFragment crashReportFragment = CrashReportFragment.newInstance(activity);
            fragmentTransaction.addToBackStack(Constants.TAG_FRAGMENT_CRASH_REPORT);
            this.currentFragment = Constants.TAG_FRAGMENT_CRASH_REPORT;
            fragmentTransaction.replace(R.id.content_frame, crashReportFragment, Constants.TAG_FRAGMENT_CRASH_REPORT).commit();

        } catch (Exception e) {
        }
    }


    /**
     * Method to main back stack of fragments when only 'Home' or 'Start' fragment should be there
     */
    public void maintainBackStack() {
        try {
            fragmentManager.popBackStack(Constants.TAG_FRAGMENT_CRASH_REPORT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
        }
    }
}
