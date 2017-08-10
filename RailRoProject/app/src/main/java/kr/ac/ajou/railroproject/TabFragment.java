package kr.ac.ajou.railroproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class TabFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment, container, false);

        tabLayout = view.findViewById(R.id.main_tab_layout);

        tabLayout.setupWithViewPager(viewPager);

        setTabText();
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = view.findViewById(R.id.main_view_pager);

        BaseTabPageAdapter baseTabPageAdapter = new BaseTabPageAdapter(getFragmentManager(), setFragmentList());
        viewPager.setAdapter(baseTabPageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    abstract void setTabText();
    abstract List<Fragment> setFragmentList();

    public TabLayout getTabLayout() {
        return tabLayout;
    }
}
