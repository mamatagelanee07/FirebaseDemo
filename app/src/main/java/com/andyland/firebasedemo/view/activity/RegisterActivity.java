package com.andyland.firebasedemo.view.activity;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initFireBase();
        uiInitialize();
    }

    private void initFireBase() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent intent = new Intent(RegisterActivity.this, FireBaseActivity.class);
                    startActivity(intent);
                } else {
                    // User is signed out
                  /*  Log.d(TAG, "onAuthStateChanged:signed_out");
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);*/
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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
        mAuth.createUserWithEmailAndPassword(email, password)
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
