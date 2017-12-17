package kr.ac.ajou.railroproject.MyInfo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.BaseTabFragment;
import kr.ac.ajou.railroproject.Board.BoardAccompaniedFragment;

public class MyInfoTabFragment extends BaseTabFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        writeButton.setVisibility(View.GONE);
    }

    @Override
    public void setTabText() {
        getTabLayout().addTab(getTabLayout().newTab().setText("내 코스"));
        getTabLayout().addTab(getTabLayout().newTab().setText("내 게시물"));
    }

    @Override
    public List<Fragment> setFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MyCourseFragment());
        fragmentList.add(new BoardAccompaniedFragment());
        return fragmentList;
    }
}
