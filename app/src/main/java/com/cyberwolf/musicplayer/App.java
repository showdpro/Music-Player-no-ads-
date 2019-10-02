package com.cyberwolf.musicplayer;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Abhi on 12/08/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Hero Light.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
