package kr.ac.ajou.railroproject;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class TravelInfoTabFragment extends TabFragment {
    @Override
    public void setTabText() {
        getTabLayout().addTab(getTabLayout().newTab().setText("관광지"));
        getTabLayout().addTab(getTabLayout().newTab().setText("행사/이벤트"));
        getTabLayout().addTab(getTabLayout().newTab().setText("맛집"));
        getTabLayout().addTab(getTabLayout().newTab().setText("숙소"));
    }

    @Override
    public List<Fragment> setFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new TourlistSpotFragment());
        fragmentList.add(new FestivalFragment());
        fragmentList.add(new RestaurantFragment());
        fragmentList.add(new AccommodationFragment());
        return fragmentList;
    }
}
