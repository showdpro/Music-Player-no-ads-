package com.cyberwolf.musicplayer.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.appthemeengine.ATE;
import com.afollestad.appthemeengine.Config;
import com.google.android.gms.ads.AdView;
import com.cyberwolf.musicplayer.R;
import com.cyberwolf.musicplayer.utils.ATEUtils;
import com.cyberwolf.musicplayer.utils.Helpers;
import com.cyberwolf.musicplayer.utils.PreferencesUtility;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyUtils;


public class MainFragment extends Fragment  {
    private TextView shade;
    private AdView mAdView;
    PreferencesUtility mPreferences;
    public ViewPager viewPager;
    private ImageView background;
    private boolean isDarkTheme;
public int pos;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferencesUtility.getInstance(getActivity());

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Hero Light.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        isDarkTheme = PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean("dark_theme", false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(
                R.layout.fragment_main, container, false);

      //  background = (ImageView) rootView.findViewById(R.id.imageView);





        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
       // ab.setDisplayHomeAsUpEnabled(true);
        /**this is the side menu button thing. now its gone but it still appears if you swipe right**/

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
            viewPager.setOffscreenPageLimit(2);
        }
         pos = viewPager.getCurrentItem();
        //viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //view.setTranslationX(-1 * view.getWidth() * position);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        final TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tabLayout.setTabMode(TabLayout.SCROLL);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setupWithViewPager(viewPager);
       // tabLayout.addTab(tabLayout.newTab().setText(""));


        Typeface hero = Typeface.createFromAsset(getContext().getAssets(),"fonts/Hero Light.otf");
        Typeface testfont = Typeface.createFromAsset(getContext().getAssets(),"fonts/Hero Light.otf");
         TextView tv;

       for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            tv=(TextView)LayoutInflater.from(getActivity()).inflate(R.layout.customtab,null);
            tv.setTypeface(testfont);
            tabLayout.getTabAt(i).setCustomView(tv);

        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tv.setTextSize(30);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
               // tv.setTextSize(25);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
               // tv.setTextSize(30);
            }
        });

      /*  viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabLayout.getTabAt(0).getCustomView().setAlpha(1.0f);
                        tabLayout.getTabAt(1).getCustomView().setAlpha(0.5f);
                        tabLayout.getTabAt(2).getCustomView().setAlpha(0.5f);
                        tabLayout.getTabAt(3).getCustomView().setAlpha(0.5f);
                        break;
                    case 1:
                        tabLayout.getTabAt(0).getCustomView().setAlpha(0.5f);
                        tabLayout.getTabAt(1).getCustomView().setAlpha(1.0f);
                        tabLayout.getTabAt(2).getCustomView().setAlpha(0.5f);
                        tabLayout.getTabAt(3).getCustomView().setAlpha(0.5f);
                        break;
                    case 2:
                        tabLayout.getTabAt(0).getCustomView().setAlpha(0.5f);
                        tabLayout.getTabAt(1).getCustomView().setAlpha(0.5f);
                        tabLayout.getTabAt(2).getCustomView().setAlpha(1.0f);
                        tabLayout.getTabAt(3).getCustomView().setAlpha(0.5f);
                        break;
                    case 3:
                        tabLayout.getTabAt(0).getCustomView().setAlpha(0.5f);
                        tabLayout.getTabAt(1).getCustomView().setAlpha(0.5f);
                        tabLayout.getTabAt(2).getCustomView().setAlpha(0.5f);
                        tabLayout.getTabAt(3).getCustomView().setAlpha(1.0f);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
*/

        tabLayout.getTabAt(1).getCustomView().setAlpha(1.0f);
        tabLayout.getTabAt(2).getCustomView().setAlpha(1.0f);
        tabLayout.getTabAt(3).getCustomView().setAlpha(1.0f);
        tabLayout.getTabAt(0).getCustomView().setAlpha(1.0f);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabLayout.getTabAt(0).getCustomView().animate().alpha(1.0f);
                        tabLayout.getTabAt(1).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(2).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(3).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(4).getCustomView().animate().alpha(0.2f);
                        break;
                    case 1:
                        tabLayout.getTabAt(0).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(1).getCustomView().animate().alpha(1.0f);
                        tabLayout.getTabAt(2).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(3).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(4).getCustomView().animate().alpha(0.2f);
                        break;
                    case 2:
                        tabLayout.getTabAt(0).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(1).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(2).getCustomView().animate().alpha(1.0f);
                        tabLayout.getTabAt(3).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(4).getCustomView().animate().alpha(0.2f);

                        break;
                    case 3:
                        tabLayout.getTabAt(0).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(1).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(2).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(3).getCustomView().animate().alpha(1.0f);
                        tabLayout.getTabAt(4).getCustomView().animate().alpha(0.2f);
                        break;
                    case 4:
                        tabLayout.getTabAt(0).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(1).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(2).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(3).getCustomView().animate().alpha(0.2f);
                        tabLayout.getTabAt(4).getCustomView().animate().alpha(1.0f);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        for (int i = 0; i < tabLayout.getChildCount(); ++i) {
            View nextChild = tabLayout.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(hero);
            }
        }

        changeFontInViewGroup(toolbar,"fonts/Hero Light.otf"); //this changes the font of timber dev for some reason..weird
        changeTabsFont(tabLayout,"fonts/herolight.otf"); //this is what changes the tab font, not layout inflater LOLOL
        return rootView;


    }
    public void onButtonClick(int pos) {
        //Change the tabs.
    }
   // HomeFragment frag = new HomeFragment();
    //frag.setTargetFragment(MainFragment.this, 300); //300- request code , you can use anything

   /* @Override
    public void onPlaylistButtonClicked() {
        // Show playlist tab (assuming playlist is the third tab).
        viewPager.setCurrentItem(pos);
    } */

    /* public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        } */
      ////////////////////////////////////////////////////////////////
      public class DepthPageTransformer implements ViewPager.PageTransformer {
          private static final float MIN_SCALE = 0.75f;

          public void transformPage(View view, float position) {
              int pageWidth = view.getWidth();

              if (position < -1) { // [-Infinity,-1)
                  // This page is way off-screen to the left.
                  view.setAlpha(0);

              } else if (position <= 0) { // [-1,0]
                  // Use the default slide transition when moving to the left page
                  //view.setAlpha(1);
                  view.setAlpha(1);
                  view.setTranslationX(0);
                  view.setScaleX(1);
                  view.setScaleY(1);

              } else if (position <= 1) { // (0,1]
                  // Fade the page out.
                  view.setAlpha(1 - position);

                  // Counteract the default slide transition
                  view.setTranslationX(pageWidth * -position);

                  // Scale the page down (between MIN_SCALE and 1)
                  float scaleFactor = MIN_SCALE
                          + (1 - MIN_SCALE) * (1 - Math.abs(position));
                  view.setScaleX(scaleFactor);
                  view.setScaleY(scaleFactor);

              } else { // (1,+Infinity]
                  // This page is way off-screen to the right.
                  view.setAlpha(0);
              }
          }
      }


    public static void changeTabsFont(TabLayout tabLayout, String fontName) {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    CalligraphyUtils.applyFontToTextView(tabLayout.getContext(), (TextView) tabViewChild, fontName);
                }
            }
        }
    }

    void changeFontInViewGroup(ViewGroup viewGroup, String fontPath) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (TextView.class.isAssignableFrom(child.getClass())) {
                CalligraphyUtils.applyFontToTextView(child.getContext(), (TextView) child, fontPath);
            } else if (ViewGroup.class.isAssignableFrom(child.getClass())) {
                changeFontInViewGroup((ViewGroup) viewGroup.getChildAt(i), fontPath);
            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean("dark_theme", false)) {
            ATE.apply(this, "dark_theme");
        } else {
            ATE.apply(this, "light_theme");
        }
        viewPager.setCurrentItem(mPreferences.getStartPageIndex());
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        //HomeFragment homeFragment = new HomeFragment();
        //homeFragment.setListener( new HomeFragmentListener());
      // adapter.addFragment(homeFragment, this.getString(R.string.Homecap));
        adapter.addFragment(new SongsFragment(), this.getString(R.string.songs));
        adapter.addFragment(new PlaylistFragment(),this.getString(R.string.Homecap));
        adapter.addFragment(new AlbumFragment(), this.getString(R.string.albums));
        adapter.addFragment(new ArtistFragment(), this.getString(R.string.artists));
        adapter.addFragment(new HomeFragment(), this.getString(R.string.Options));
        //adapter.addFragment(new eqFragment(), this.getString(R.string.eq));
        //adapter.addFragment(new FoldersFragment(),this.getString(R.string.folders));




        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPreferences.lastOpenedIsStartPagePreference()) {
            mPreferences.setStartPageIndex(viewPager.getCurrentItem());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String ateKey = Helpers.getATEKey(getActivity());
        ATEUtils.setStatusBarColor(getActivity(), ateKey, Config.primaryColor(getActivity(), ateKey));

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
