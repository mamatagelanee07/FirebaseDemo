package com.andyland.firebasedemo.common.util;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.view.activity.FireBaseActivity;
import com.andyland.firebasedemo.view.fragment.CrashReportFragment;
import com.andyland.firebasedemo.view.fragment.FeedbackFragment;
import com.andyland.firebasedemo.view.fragment.ProfileFragment;

/**
 * Created by Andy on 10/16/2015.
 */
public class FragmentLoader {

    private static final String TAG = FragmentLoader.class.getSimpleName();
    private FragmentManager fragmentManager;
    private String currentFragment;
    private static FragmentLoader fragmentLoader;
    private FireBaseActivity fireBaseActivity;

    /*  public static FragmentLoader newInstance(FireBaseActivity coreActivity) {
          if (fragmentLoader == null)
              fragmentLoader = new FragmentLoader(coreActivity);
          return fragmentLoader;
      }
  */
    public FragmentLoader(FireBaseActivity coreActivity) {
        this.fragmentManager = coreActivity.getSupportFragmentManager();
        this.fireBaseActivity = coreActivity;
    }

    public String getCurrentFragment() {
        return currentFragment;
    }

    /**
     * Attempts to load CrashFragment
     */
    public void loadCrashReportFragment() {
        try {

            boolean fragmentPopped = fragmentManager.popBackStackImmediate(Constants.TAG_FRAGMENT_CRASH_REPORT, 0);
            boolean isAddable = !fragmentPopped && fragmentManager.findFragmentByTag(Constants.TAG_FRAGMENT_CRASH_REPORT) == null;
            if (!isAddable) {
                fragmentManager.popBackStack(Constants.TAG_FRAGMENT_CRASH_REPORT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CrashReportFragment crashReportFragment = CrashReportFragment.newInstance(fireBaseActivity);
            fragmentTransaction.addToBackStack(Constants.TAG_FRAGMENT_CRASH_REPORT);
            this.currentFragment = Constants.TAG_FRAGMENT_CRASH_REPORT;
            fragmentTransaction.replace(R.id.content_frame, crashReportFragment, Constants.TAG_FRAGMENT_CRASH_REPORT).commit();

        } catch (Exception e) {
        }
    }


    /**
     * Attempts to load FeedbackFragment
     */
    public void loadFeedbackFragment() {
        try {

            boolean fragmentPopped = fragmentManager.popBackStackImmediate(Constants.TAG_FRAGMENT_FEEDBACK, 0);
            boolean isAddable = !fragmentPopped && fragmentManager.findFragmentByTag(Constants.TAG_FRAGMENT_FEEDBACK) == null;
            if (!isAddable) {
                fragmentManager.popBackStack(Constants.TAG_FRAGMENT_FEEDBACK, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FeedbackFragment feedbackFragment = FeedbackFragment.newInstance(fireBaseActivity);
            fragmentTransaction.addToBackStack(Constants.TAG_FRAGMENT_FEEDBACK);
            this.currentFragment = Constants.TAG_FRAGMENT_FEEDBACK;
            fragmentTransaction.replace(R.id.content_frame, feedbackFragment, Constants.TAG_FRAGMENT_FEEDBACK).commit();

        } catch (Exception e) {
        }
    }

    /**
     * Attempts to load FeedbackFragment
     */
    public void loadProfileFragment() {
        try {
            boolean fragmentPopped = fragmentManager.popBackStackImmediate(Constants.TAG_FRAGMENT_PROFILE, 0);
            boolean isAddable = !fragmentPopped && fragmentManager.findFragmentByTag(Constants.TAG_FRAGMENT_PROFILE) == null;
            if (!isAddable) {
                fragmentManager.popBackStack(Constants.TAG_FRAGMENT_PROFILE, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ProfileFragment profileFragment = ProfileFragment.newInstance(fireBaseActivity);
            fragmentTransaction.addToBackStack(Constants.TAG_FRAGMENT_PROFILE);
            this.currentFragment = Constants.TAG_FRAGMENT_PROFILE;
            fragmentTransaction.replace(R.id.content_frame, profileFragment, Constants.TAG_FRAGMENT_PROFILE).commit();

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
