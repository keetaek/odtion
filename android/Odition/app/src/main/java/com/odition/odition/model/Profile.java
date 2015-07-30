package com.odition.odition.model;

import com.parse.ParseUser;

/**
 * Created by Jesus on 7/28/2015.
 */
public class Profile extends ParseUser {
    // username, password, and email are inherited

    public String getCity() {
        return getString("city");
    }

    public void setCity(String city) {
        put("city", city);
    }
}
