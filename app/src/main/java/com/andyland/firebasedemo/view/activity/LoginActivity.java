package com.andyland.firebasedemo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.common.util.Constants;
import com.andyland.firebasedemo.common.util.FontLoader;
import com.andyland.firebasedemo.service.authentication.FacebookSignInHelper;
import com.andyland.firebasedemo.service.authentication.FireBaseAuthHelper;
import com.andyland.firebasedemo.service.authentication.GoogleSignInHelper;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    // UI references.
    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;
    @BindView(R.id.email_sign_in_button)
    Button mEmailSignInButton;
    @BindView(R.id.txtSignUp)
    TextView txtSignUp;
    @BindView(R.id.google_sign_in_button)
    Button btnGoogleSignIn;
    @BindView(R.id.btnFacebookLogin)
    LoginButton btnFacebookLogin;
    private FireBaseAuthHelper fireBaseAuthHelper;
    private GoogleSignInHelper googleSignInHelper;
    private FacebookSignInHelper facebookSignInHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initFireBase();
        initUI();
    }

    private void initFireBase() {
        fireBaseAuthHelper = new FireBaseAuthHelper(LoginActivity.this);
        fireBaseAuthHelper.setActivity(LoginActivity.this);
        googleSignInHelper = GoogleSignInHelper.newInstance(LoginActivity.this, fireBaseAuthHelper);
        facebookSignInHelper = FacebookSignInHelper.newInstance(LoginActivity.this, fireBaseAuthHelper);
        btnFacebookLogin.registerCallback(facebookSignInHelper.getCallbackManager(),
                facebookSignInHelper.getLoginCallback());
    }

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebookSignInHelper.getCallbackManager().onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GoogleSignInHelper.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                googleSignInHelper.getGoogleAccountDetails(result);
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                Log.d(TAG, "signInWith Google failed");
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]


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

    private void loginUser(String email, String password) {
        fireBaseAuthHelper.getFireBaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmailAndPassword:onComplete:" + task.isSuccessful());
                    }
                });
    }

    /**
     * Attempts to initialize all UI resources
     */
    private void initUI() {
        setTypeFace();
    }

    /**
     * Attempts to load custom font
     */
    private void setTypeFace() {
        FontLoader fontLoader = FontLoader.getInstance(LoginActivity.this);
        fontLoader.setTypeFace(mEmailView, Constants.FONT_SANSATION_LIGHT);
        fontLoader.setTypeFace(mPasswordView, Constants.FONT_SANSATION_LIGHT);
        fontLoader.setTypeFace(mEmailSignInButton, Constants.FONT_SANSATION_BOLD);
        fontLoader.setTypeFace(txtSignUp, Constants.FONT_GOOD_DOG);
        fontLoader.setTypeFace(btnGoogleSignIn, Constants.FONT_SANSATION_BOLD);
    }

    @OnClick(R.id.txtSignUp)
    void openSignUpScreen() {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @OnClick(R.id.email_sign_in_button)
    void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            loginUser(email, password);
        }
    }

    @OnClick(R.id.google_sign_in_button)
    // [START signin]
    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleSignInHelper.getGoogleClient());
        startActivityForResult(signInIntent, GoogleSignInHelper.RC_SIGN_IN);
    }

    // [END signin]
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}

