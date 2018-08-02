package pme.ai.fhe.de.studybuddy.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pme.ai.fhe.de.studybuddy.fragments.BarChartFragment;
import pme.ai.fhe.de.studybuddy.fragments.LineChartFragment;
import pme.ai.fhe.de.studybuddy.fragments.PieChartFragment;

/**
 * handles the page indicator and which fragment should be called
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private int NUM_ITEMS = 3;

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     *
     * @return Returns total number of pages
     */
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


    /**
     *
     * @param position actual position of page indicator
     * @return Returns the fragment to display for that page
     */
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

    /**
     *
     * @param position actual position of page indicator
     * @return Returns the page title for the top indicator
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}
