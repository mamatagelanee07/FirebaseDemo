package com.andyland.firebasedemo.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.common.util.Constants;
import com.andyland.firebasedemo.common.util.FontLoader;
import com.andyland.firebasedemo.service.authentication.FireBaseAuthHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    private String TAG = RegisterActivity.class.getSimpleName();

    @BindView(R.id.ic_firebase)
    ImageView imgFireBase;
    @BindView(R.id.txt_title_firebase)
    TextView txtTitleFireBase;
    @BindView(R.id.input_password)
    EditText edtPassword;
    @BindView(R.id.input_email)
    EditText edtEmail;
    @BindView(R.id.input_contact)
    EditText edtContact;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private FireBaseAuthHelper fireBaseAuthHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initFireBase();
        uiInitialize();
    }

    private void initFireBase() {
        fireBaseAuthHelper = new FireBaseAuthHelper(RegisterActivity.this);
        fireBaseAuthHelper.setActivity(RegisterActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fireBaseAuthHelper.addAuthListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fireBaseAuthHelper.removeAuthListener();
    }

    private void uiInitialize() {
        try {
            setTypeFace();
            txtTitleFireBase.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.btn_submit)
    public void onSubmitClicked() {
        // TODO : Register user to firebase using email/password
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        registerUser(email, password);
    }

    private void registerUser(String email, String password) {
        fireBaseAuthHelper.getFireBaseAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                    }
                });
    }


    /**
     * Attempts to load custom fonts
     */
    private void setTypeFace() {
        FontLoader fontLoader = FontLoader.getInstance(RegisterActivity.this);
        fontLoader.setTypeFace(txtTitleFireBase, Constants.FONT_GOOD_DOG);
        fontLoader.setTypeFace(edtPassword, Constants.FONT_SANSATION_LIGHT);
        fontLoader.setTypeFace(edtEmail, Constants.FONT_SANSATION_LIGHT);
        fontLoader.setTypeFace(edtContact, Constants.FONT_SANSATION_LIGHT);
    }
}
