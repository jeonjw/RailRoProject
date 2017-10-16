package kr.ac.ajou.railroproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinwoo on 2017. 8. 10..
 */

public class CommunityFragment extends TabFragment {
    @Override
    void setTabText() {
        getTabLayout().addTab(getTabLayout().newTab().setText("팁"));
        getTabLayout().addTab(getTabLayout().newTab().setText("동행"));
        getTabLayout().addTab(getTabLayout().newTab().setText("추천"));
    }

    @Override
    List<Fragment> setFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new TourlistSpotFragment());
        fragmentList.add(new FestivalFragment());
        fragmentList.add(new RestaurantFragment());
        fragmentList.add(new AccommodationFragment());
        return fragmentList;
    }
}
