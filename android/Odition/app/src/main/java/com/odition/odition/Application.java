package com.odition.odition;

import com.facebook.FacebookSdk;
import com.odition.odition.model.Profile;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

        Parse.initialize(this, getString(R.string.parse_app_id),
                getString(R.string.parse_client_key));

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        ParseFacebookUtils.initialize(getApplicationContext());

        ParseTwitterUtils.initialize(getString(R.string.twitter_consumer_key),
                getString(R.string.twitter_consumer_secret));
    }
}
