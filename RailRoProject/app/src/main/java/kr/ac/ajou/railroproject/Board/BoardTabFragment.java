package kr.ac.ajou.railroproject.Board;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.TabFragment;


public class BoardTabFragment extends TabFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        writeButton.setVisibility(View.VISIBLE);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                intent.putExtra("CURRENT_BOARD_TAB", TabFragment.getCurrentTab());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setTabText() {
        getTabLayout().addTab(getTabLayout().newTab().setText("팁"));
        getTabLayout().addTab(getTabLayout().newTab().setText("동행"));
        getTabLayout().addTab(getTabLayout().newTab().setText("추천"));
    }

    @Override
    public List<Fragment> setFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new BoardTipFragment());
        fragmentList.add(new BoardAccompaniedFragment());
        fragmentList.add(new BoardRecommendationFragment());
        return fragmentList;
    }
}
