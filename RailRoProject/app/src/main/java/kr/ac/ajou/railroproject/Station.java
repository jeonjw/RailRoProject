package kr.ac.ajou.railroproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinwoo on 2017. 10. 16..
 */

class Station {
    private String stationName;
    private List<Place> placeList = new ArrayList<Place>();

    public Station() {
    }

    public Station(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName){
        this.stationName = stationName;
    }

    public List getPlaceList(){
        return placeList;
    }

    public void setPlaceList(List<Place> placeList){
        this.placeList = placeList;
    }

    public void addPlace(Place place){
        placeList.add(place);
    }

    public void remove(Place place){
        placeList.remove(place);
    }


}
