package com.andyland.firebasedemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.crash.FirebaseCrash;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_generate_crash)
    Button btnGenerateCrash;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    /**
     * To generate ArithmeticException : divide by zero
     * Note : It will take around 20 minutes to display same exception on Firebase crash dashboard.
     * (I have fired this exception on 11:38am and got report at 11:48am.)
     */
    @OnClick(R.id.btn_generate_crash)
    public void generateCrash() {
        // We have added below code to generate ArithmeticException and report this crash on Firebase Crash Reporting.
        int a = 10 / 0;
    }

    private void createCustomLog(String message) {
        FirebaseCrash.log(message);
    }

    /**
     * To add custom toolbar in Activity
     */
    private void addToolbar() {
        try {
//            toolbar.setTitle(getString(R.string.app_name));
            setSupportActionBar(toolbar);
//            if (getSupportActionBar() != null)
//                getSupportActionBar().setTitle(getString(R.string.app_name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Attempts to initialize all resources here.
     */
    private void initUI() {
        try {
            ButterKnife.bind(this);
            addToolbar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.fab)
    public void onFABClicked() {
        Snackbar.make(floatingActionButton, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
