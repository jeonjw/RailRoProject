package kr.ac.ajou.railroproject.TourInfo;

public class TourListSpotFragment extends BaseTourFragment {


    @Override
    public String getApiRequest() {
        return "areaBasedList";
    }

    @Override
    public String getContentTypeId() {
        return "12";
    }
}
