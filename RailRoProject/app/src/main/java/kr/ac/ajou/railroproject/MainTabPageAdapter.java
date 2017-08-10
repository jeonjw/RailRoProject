package kr.ac.ajou.railroproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class MainTabPageAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public MainTabPageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new CourseFragment();

            case 2:
                return new CommunityFragment();

            case 3:
                return new MyInfoFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
