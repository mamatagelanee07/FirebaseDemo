package com.andyland.firebasedemo.service.authentication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.andyland.firebasedemo.view.activity.FireBaseActivity;
import com.andyland.firebasedemo.view.activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Andy
 */
public class FireBaseAuthHelper {
    private static FireBaseAuthHelper fireBaseAuthHelper;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Activity mActivity;

    public static FireBaseAuthHelper newInstance(Activity mActivity) {
        if (fireBaseAuthHelper == null) {
            fireBaseAuthHelper = new FireBaseAuthHelper(mActivity);
        }
        return fireBaseAuthHelper;
    }

    public FireBaseAuthHelper(Activity mActivity) {
        this.mActivity = mActivity;
        initFireBase();
    }

    private void initFireBase() {
        firebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    openScreen(FireBaseActivity.class);
                } else {
                    openScreen(LoginActivity.class);
                }
            }
        };
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public FirebaseAuth getFireBaseAuth() {
        return firebaseAuth;
    }

    public FirebaseUser getFireBaseUser() {
        return firebaseAuth.getCurrentUser();
    }

    public void signOut() {
        firebaseAuth.signOut();
    }

    private void openScreen(Class mClass) {
        Intent intent = new Intent(this.mActivity, mClass);
        this.mActivity.startActivity(intent);
    }

    public void removeAuthListener() {
        if (mAuthListener != null)
            firebaseAuth.removeAuthStateListener(mAuthListener);
    }
}
