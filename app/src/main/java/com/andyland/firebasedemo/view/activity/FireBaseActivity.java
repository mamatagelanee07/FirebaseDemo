package com.andyland.firebasedemo.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.andyland.firebasedemo.R;
import com.andyland.firebasedemo.common.util.FragmentLoader;
import com.andyland.firebasedemo.service.authentication.FireBaseAuthHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

// TODO: Add non-fatal logging to app
public class FireBaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    public FragmentLoader fragmentLoader;
    public FireBaseAuthHelper fireBaseAuthHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        ButterKnife.bind(this);
        initFireBase();
        initUI();
    }

    private void initFireBase() {
        fireBaseAuthHelper = FireBaseAuthHelper.newInstance(FireBaseActivity.this);
        fireBaseAuthHelper.setActivity(FireBaseActivity.this);
    }

    /**
     * Attempts to initialize all activity resources
     */
    private void initUI() {
        fragmentLoader = new FragmentLoader(FireBaseActivity.this);
        // set toolbar
        setSupportActionBar(toolbar);

        // set drawer listener
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // set navigation item listener
        navigationView.setNavigationItemSelectedListener(this);

        // load default fragment when activity just created
        loadDefaultFragment();
    }

    private void loadDefaultFragment() {
        fragmentLoader.loadCrashReportFragment();
    }


    @Override
    public void onBackPressed() {
        try {
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else if (backStackEntryCount == 1) {
                FireBaseActivity.this.finish();
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.firebase, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        FireBaseAuthHelper.newInstance(FireBaseActivity.this).signOut();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_crash:
                fragmentLoader.loadCrashReportFragment();
                break;
            case R.id.nav_database:
                fragmentLoader.loadFeedbackFragment();
                break;
            case R.id.nav_profile:
                fragmentLoader.loadProfileFragment();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
