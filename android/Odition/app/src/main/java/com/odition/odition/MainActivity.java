package com.odition.odition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.odition.odition.fragments.HomeFragment;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

public class MainActivity extends AppCompatActivity
        implements
        HomeFragment.OnLogoutListener {

    public static final int LOGIN_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance()).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ParseUser.getCurrentUser() == null) {
            launchLoginActivity();
        }
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


    @Override
    public void onLogout() {
        launchLoginActivity();
    }

    protected void launchLoginActivity() {
        Intent loginIntent = new ParseLoginBuilder(this)
                .setFacebookLoginEnabled(true)
                .setTwitterLoginEnabled(true)
                .setParseLoginEmailAsUsername(true)
                .setParseSignupMinPasswordLength(8)
                .setAppLogo(R.drawable.audition_logo)
                .build();
        startActivityForResult(loginIntent, LOGIN_REQUEST);
    }
}
