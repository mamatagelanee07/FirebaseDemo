package com.andyland.firebasedemo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.common.util.Constants;
import com.andyland.firebasedemo.common.util.FontLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.contentView)
    View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(SplashActivity.this);
        initUI();
    }

    private void initUI() {
        setTypeFace();
    }

    /**
     * Attempts to load custom fonts
     */
    private void setTypeFace() {
        FontLoader fontLoader = FontLoader.getInstance(SplashActivity.this);
        fontLoader.setTypeFace(txtTitle, Constants.FONT_GOOD_DOG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler handler = new Handler();
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, Constants.SPLASH_SLEEP_TIME);

        contentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    Runnable runnable = new Runnable() {
        public void run() {
            navigateUser();
            SplashActivity.this.finish();

        }
    };

    private void navigateUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            startActivity(new Intent(SplashActivity.this, FireBaseActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
    }

}
