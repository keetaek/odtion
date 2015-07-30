package com.odition.odition.util;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Jesus on 7/28/2015.
 */
public class ViewActions {
    public  static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setEnabled(false);
        }
    };

    public static final ButterKnife.Action<View> ENABLE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setEnabled(true);
        }
    };


    public static final ButterKnife.Action<View> VISIBLE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setVisibility(View.VISIBLE);
        }
    };

    public static final ButterKnife.Action<View> GONE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setVisibility(View.GONE);
        }
    };
}
