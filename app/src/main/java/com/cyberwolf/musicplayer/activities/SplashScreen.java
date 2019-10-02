package com.cyberwolf.musicplayer.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cyberwolf.musicplayer.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface hero = Typeface.createFromAsset(getAssets(),"fonts/Hero Light.otf");
       // Typeface gidole = Typeface.createFromAsset(getAssets(),"fonts/Gidole-Regular.otf");
       // Typeface nooa = Typeface.createFromAsset(getAssets(),"fonts/nooa.ttf");
        final EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(1400)
                .withLogo(R.drawable.fireskullsmall)
                .withBackgroundColor(Color.BLACK)

                .withBeforeLogoText("c y b e r w o l f");

        config.getBeforeLogoTextView().setTextColor(Color.WHITE);

        config.getBeforeLogoTextView().setTypeface(hero);

        /*config.getAfterLogoTextView().setTypeface(gidole);
        config.getAfterLogoTextView().setTextColor(Color.RED);
        config.getAfterLogoTextView().setTextSize(40);
        config.getAfterLogoTextView().setAlpha(0);
        config.getAfterLogoTextView().animate().alpha(1.0f).setDuration(1200);*/

        config.getBeforeLogoTextView().setTextSize(35);

        config.getBeforeLogoTextView().setAlpha(0.0f);

        config.getLogo().animate().alpha(0.0f).translationY(250).setDuration(1200); //need to do this cus non transparent png with solid borders looks ugly
        config.getBeforeLogoTextView().animate().translationY(250).alpha(1.0f).setDuration(1200);








        View view = config.create();
        setContentView(view);




    }
}
