package kr.ac.ajou.railroproject.TourInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import kr.ac.ajou.railroproject.R;

public class AccommodationFragment extends BaseTourFragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.location_based_search).setVisibility(View.GONE);
    }

    @Override
    public String getApiRequest() {
        return "searchStay";
    }

    @Override
    public String getContentTypeId() {
        return "32";
    }
}
