package pme.ai.fhe.de.studybuddy.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;


import com.facebook.stetho.Stetho;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import pme.ai.fhe.de.studybuddy.fragments.BarChartFragment;
import pme.ai.fhe.de.studybuddy.fragments.LineChartFragment;
import pme.ai.fhe.de.studybuddy.fragments.PieChartFragment;
import pme.ai.fhe.de.studybuddy.R;

/**
 * This activity shows the charts packed in fragments. it handles the change between this fragments
 */
public class OverviewActivity extends MenuActivity {
    FragmentPagerAdapter adapterViewPager;


    /**
     * connects the layout with the xml files and opens the menu
     * sets the page indicator
     * @param savedInstanceState reference to a Bundle object that is passed
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        setTitle("Übersicht");

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new pme.ai.fhe.de.studybuddy.utilities.PagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        Stetho.initializeWithDefaults(this);

        InkPageIndicator inkPageIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(vpPager);

        openMenu();

    }






}



