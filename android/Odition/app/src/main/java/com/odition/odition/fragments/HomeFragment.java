package com.odition.odition.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.odition.odition.R;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    @Bind(R.id.txt_greetings)
    TextView greetingsTextView;

    private OnLogoutListener mListener;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Activity  activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLogoutListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnLogoutListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            greetingsTextView.setText("Hello, " + user.getUsername());
        } else {
            greetingsTextView.setText("Hello, stranger");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.btn_logout)
    public void onLogout(Button button) {
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                mListener.onLogout();
            }
        });
    }

    public interface OnLogoutListener {
        void onLogout();
    }


}
