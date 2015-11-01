package com.project.jholt_000.pamyupamyulyrics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity3 extends FragmentActivity {

    private static final int NUM_PAGES = 3;

    private ViewPager mPager;
    private String name;
    private ArrayList<String> mTagArrayList;
    private String strSavedMem1;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        mTagArrayList = new ArrayList<String>();
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        Intent intent = getIntent();
        name = intent.getExtras().getString("song");

        TextView tv = (TextView) findViewById(R.id.tvFragFirst);
        tv.setText(name);
        Typeface myCustomFont = Typeface.createFromAsset(this.getAssets(), "fonts/himaji.ttf");
        tv.setTypeface(myCustomFont);
        ImageButton imgbtn = (ImageButton) findViewById(R.id.imageButton);
        imgbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        LoadPreferences();
        if(strSavedMem1.equals("")) {
            Toast.makeText(getApplicationContext(), R.string.swipe_instructions,
                    Toast.LENGTH_LONG).show();
            SavePreferences("MEM1", "NOTNULL");
        }

    }


    public void goHome() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            FirstFragment mFragment = FirstFragment.newInstance(name + "j1");;
            if (pos == 0)
                mFragment = FirstFragment.newInstance(name + "j1");
            else if (pos == 1)
                mFragment = FirstFragment.newInstance(name + "r");
            else if (pos == 2)
                mFragment = FirstFragment.newInstance(name + "e");
            return mFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private void SavePreferences(String key, String value){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        strSavedMem1 = sharedPreferences.getString("MEM1", "");
    }

}