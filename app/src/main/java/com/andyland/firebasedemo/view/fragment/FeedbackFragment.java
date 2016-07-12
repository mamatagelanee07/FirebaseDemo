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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.common.util.Constants;
import com.andyland.firebasedemo.common.util.FontLoader;
import com.andyland.firebasedemo.common.util.Logger;
import com.andyland.firebasedemo.vo.FeedbackVO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackFragment extends Fragment {
    private String TAG = FeedbackFragment.class.getSimpleName();
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
    @BindView(R.id.input_contact)
    EditText edtContact;
    //    @BindView(R.id.input_comment)
//    EditText edtComment;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.rate_app)
    RatingBar ratingBar;


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

                setTypeFace();
                txtTitleFireBase.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.btn_submit)
    public void onSubmitClicked() {
        getUserFeedback();
    }

    private void validUserFeedback() {
        boolean isValidFeedback = true;
        FeedbackVO feedbackVO = getUserFeedback();
        if (feedbackVO.getUsername() != null && feedbackVO.getUsername().equals(Constants.DEFAULT_STRING)) {
            edtName.requestFocus();
            edtName.setError(mActivity.getString(R.string.err_fill_field));
            isValidFeedback = false;
        }
    }

    private FeedbackVO getUserFeedback() {
        FeedbackVO feedbackVO = new FeedbackVO();
        feedbackVO.setUsername(edtName.getText().toString());
        feedbackVO.setUserEmail(edtEmail.getText().toString());
        feedbackVO.setUserContact(edtContact.getText().toString());
        feedbackVO.setRating(ratingBar.getRating());
        return feedbackVO;
    }

    private void setTypeFace() {
        FontLoader fontLoader = FontLoader.getInstance(mActivity);
        fontLoader.setTypeFace(txtTitleFireBase, Constants.FONT_GOOD_DOG);
        fontLoader.setTypeFace(edtName, Constants.FONT_SANSATION_LIGHT);
        fontLoader.setTypeFace(edtEmail, Constants.FONT_SANSATION_LIGHT);
        fontLoader.setTypeFace(edtContact, Constants.FONT_SANSATION_LIGHT);
//        fontLoader.setTypeFace(edtComment, Constants.FONT_SANSATION_LIGHT);
    }
}