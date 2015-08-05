package com.odition.odition;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.odition.odition.fragments.CreateAuditionFragment;
import com.odition.odition.fragments.HomeFragment;
import com.odition.odition.util.GravatarUrl;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int LOGIN_REQUEST = 0;
    public static final String SELECTED_ID = "SELECTED_ID";

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.main_drawer)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ActionBarDrawerToggle drawerToggle;
    private int selectedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        initDrawer();

        //default it set first item as selected
        selectedId = savedInstanceState == null ? R.id.nav_home : savedInstanceState.getInt("SELECTED_ID");
        itemSelection(selectedId);
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
        } else {
            ParseUser user = ParseUser.getCurrentUser();
            String email = user.getEmail();
            GravatarUrl gravatarUrl = new GravatarUrl(email);

            ImageView imageView = (ImageView) navigationView.findViewById(R.id.img_avatar);
            TextView textView = (TextView) navigationView.findViewById(R.id.txt_username);
            textView.setText(email);

            Picasso.with(this).load(gravatarUrl.toString()).into(imageView);
        }
    }

    private void initDrawer() {
        navigationView.setNavigationItemSelectedListener(this);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        selectedId = menuItem.getItemId();
        itemSelection(selectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt(SELECTED_ID, selectedId);
    }

    public void itemSelection(int mSelectedId) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (mSelectedId) {
            case R.id.nav_home:
                navto(HomeFragment.newInstance());
                break;

            case R.id.nav_create_audition:
                navto(new CreateAuditionFragment());
                break;

            case R.id.nav_logout:
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        launchLoginActivity();
                    }
                });
                break;

            default:
                throw new IllegalArgumentException("Navigation Selection is invalid");
        }
    }

    protected void navto(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
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
