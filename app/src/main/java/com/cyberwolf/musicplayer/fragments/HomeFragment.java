package com.cyberwolf.musicplayer.fragments;

/**
 * Created by turtleSQUAD98 on 08/10/2017.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import com.afollestad.appthemeengine.prefs.ATECheckBoxPreference;
import com.afollestad.appthemeengine.prefs.ATEColorPreference;
import com.cyberwolf.musicplayer.activities.MainActivity;
import com.cyberwolf.musicplayer.utils.NavigationUtils;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.cyberwolf.musicplayer.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.widget.Button;

public class HomeFragment extends Fragment{
    private Button enable,disable,settings;
    private ThemeChanged listener;
    private AdView mAdView;
   //SharedPreferences pref = getActivity().getSharedPreferences("Theme", Context.MODE_PRIVATE);
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_home, container, false);
        enable = (Button) rootView.findViewById(R.id.enable);
       /* int value= pref.getInt("yourTheme", 1);//1 is default value
        if(value==2) {
            enable.setVisibility(View.GONE);
        }else if(value==1){
            disable.setVisibility(View.GONE);

        } */
        /*mAdView = (AdView) rootView.findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 pref = getActivity().getSharedPreferences("Theme", Context.MODE_PRIVATE);
                editor = pref.edit();
                //SharedPreferences.Editor editor = pref.edit();
                editor.putInt("yourTheme", 2);
                editor.commit();
                listener.themechanged(2);
                enable.setVisibility(View.INVISIBLE);
                disable.setVisibility(View.VISIBLE);



            }
        });

        settings = (Button) rootView.findViewById(R.id.settings);
        settings.setVisibility(View.GONE);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // NavigationUtils.navigateToSettings(this);
            }
        });








        disable = (Button) rootView.findViewById(R.id.disable);
        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("yourTheme", 1);
                editor.commit();
                listener.themechanged(1);
                disable.setVisibility(View.INVISIBLE);
                enable.setVisibility(View.VISIBLE);
            }
        });


        return rootView;
    }
    public interface ThemeChanged {
        public void themechanged(int value);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ThemeChanged) {
            listener = (ThemeChanged) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    // may also be triggered from the Activity
  /*  public void updateDetail(String uri) {
        // create a string just for testing
        String newTime = String.valueOf(System.currentTimeMillis());

        // inform the Activity about the change based
        // interface defintion
        listener.themechanged(newTime);
    }*/

}
