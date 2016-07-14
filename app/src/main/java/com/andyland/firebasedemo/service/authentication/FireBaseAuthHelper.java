package com.andyland.firebasedemo.service.authentication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.andyland.firebasedemo.view.activity.FireBaseActivity;
import com.andyland.firebasedemo.view.activity.LoginActivity;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;

/**
 * Created by Andy
 */
public class FireBaseAuthHelper {
    private static final String TAG = FireBaseAuthHelper.class.getSimpleName();
    //    private static FireBaseAuthHelper fireBaseAuthHelper;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Activity mActivity;

    /*public static FireBaseAuthHelper newInstance(Activity mActivity) {
        if (fireBaseAuthHelper == null) {
            fireBaseAuthHelper = new FireBaseAuthHelper(mActivity);
        }
        return fireBaseAuthHelper;
    }*/

    public FireBaseAuthHelper(Activity mActivity) {
        this.mActivity = mActivity;
        initFireBase();
    }

    private void initFireBase() {
        firebaseAuth = FirebaseAuth.getInstance();
        initializeAuthListener();
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public FirebaseAuth getFireBaseAuth() {
        return firebaseAuth;
    }

    private void initializeAuthListener() {
        if (mAuthListener == null) {
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        if (!(mActivity instanceof FireBaseActivity))
                            openScreen(FireBaseActivity.class);
                    } else {
                        if (!(mActivity instanceof LoginActivity))
                            openScreen(LoginActivity.class);
                    }
                }
            };
        } else {
            Log.i(TAG, "Auth listener is already initialized");
        }
    }

    public FirebaseUser getFireBaseUser() {
        return firebaseAuth.getCurrentUser();
    }

    public void signOut() {
        ArrayList<String> providers = (ArrayList<String>) getFireBaseUser().getProviders();
        if (providers != null && providers.size() > 0) {
            for (String provider : providers) {
                switch (provider) {
                    case GoogleAuthProvider.PROVIDER_ID:
                        GoogleSignInHelper.newInstance((AppCompatActivity) mActivity, this).signOut();
                        break;
                    case EmailAuthProvider.PROVIDER_ID:
                        firebaseAuth.signOut();
                        break;
                }
            }
        }
    }

    private void openScreen(Class mClass) {
        Intent intent = new Intent(this.mActivity, mClass);
        this.mActivity.startActivity(intent);
    }

    public void removeAuthListener() {
        if (mAuthListener != null)
            firebaseAuth.removeAuthStateListener(mAuthListener);
    }

    public void addAuthListener() {
        if (mAuthListener == null) {
            initializeAuthListener();
        }
        firebaseAuth.addAuthStateListener(mAuthListener);
    }
}
