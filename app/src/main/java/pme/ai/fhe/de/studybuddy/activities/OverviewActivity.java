package pme.ai.fhe.de.studybuddy.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import com.facebook.stetho.Stetho;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import pme.ai.fhe.de.studybuddy.fragments.BarChartFragment;
import pme.ai.fhe.de.studybuddy.fragments.LineChartFragment;
import pme.ai.fhe.de.studybuddy.fragments.PieChartFragment;
import pme.ai.fhe.de.studybuddy.R;


public class OverviewActivity extends MenuActivity {
    FragmentPagerAdapter adapterViewPager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        setTitle("Übersicht");

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        Stetho.initializeWithDefaults(this);

        InkPageIndicator inkPageIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(vpPager);

        openMenu();

    }




    public class MyPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return PieChartFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 1 - This will show SecondFragment different title
                    return BarChartFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 2 - This will show ThirdFragment different title
                    return LineChartFragment.newInstance(2, "Page # 3");
                default:
                    return PieChartFragment.newInstance(0, "Page # 1");
                    //return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }


}



