package com.andyland.firebasedemo.view.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.common.util.Constants;
import com.andyland.firebasedemo.common.util.FontLoader;
import com.andyland.firebasedemo.common.util.Logger;
import com.andyland.firebasedemo.view.activity.FireBaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {
    private String TAG = ProfileFragment.class.getSimpleName();
    private View rootView;
    private Activity mActivity;

    @BindView(R.id.ic_firebase)
    ImageView imgFireBase;
    @BindView(R.id.txt_title_firebase)
    TextView txtTitleFireBase;
    @BindView(R.id.input_name)
    EditText edtName;
    @BindView(R.id.input_email)
    EditText edtEmail;
    @BindView(R.id.input_password)
    EditText edtPassword;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private ProgressDialog progressDialog;


    public static ProfileFragment newInstance(Activity activity) {
        ProfileFragment crashReportFragment = new ProfileFragment();
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
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        return rootView;
    }

    private void uiInitialize(View rootView) {
        try {
            if (mActivity != null && rootView != null) {
                ButterKnife.bind(this, rootView);
                setTypeFace();
                txtTitleFireBase.requestFocus();
                progressDialog = new ProgressDialog(mActivity);
                displayUserInformation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayUserInformation() {
        FirebaseUser firebaseUser = ((FireBaseActivity) mActivity).fireBaseAuthHelper.getFireBaseUser();
        edtEmail.setText(firebaseUser.getEmail());
        edtName.setText(firebaseUser.getDisplayName());
        txtTitleFireBase.setText(firebaseUser.getDisplayName());
        Picasso.with(mActivity).load(firebaseUser.getPhotoUrl())
                .centerInside()
                .resize(100, 100)
                .into(imgFireBase);
    }

    @OnClick(R.id.btn_submit)
    public void updateUserProfile() {
        FirebaseUser firebaseUser = ((FireBaseActivity) mActivity).fireBaseAuthHelper.getFireBaseUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(edtName.getText().toString())
                .setPhotoUri(Uri.parse("https://d30y9cdsu7xlg0.cloudfront.net/png/17241-200.png"))
                .build();
        progressDialog.show();
        firebaseUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                            updateUserEmail();
                        } else {
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    private void updateUserEmail() {
        FirebaseUser firebaseUser = ((FireBaseActivity) mActivity).fireBaseAuthHelper.getFireBaseUser();

        firebaseUser.updateEmail(edtEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User email address updated.");
                            updatePassword();
                        } else {
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    private void updatePassword() {
        FirebaseUser firebaseUser = ((FireBaseActivity) mActivity).fireBaseAuthHelper.getFireBaseUser();
        String newPassword = edtPassword.getText().toString();

        firebaseUser.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User password updated.");
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    private void setTypeFace() {
        FontLoader fontLoader = FontLoader.getInstance(mActivity);
        fontLoader.setTypeFace(txtTitleFireBase, Constants.FONT_GOOD_DOG);
        fontLoader.setTypeFace(edtName, Constants.FONT_SANSATION_LIGHT);
        fontLoader.setTypeFace(edtEmail, Constants.FONT_SANSATION_LIGHT);
    }
}