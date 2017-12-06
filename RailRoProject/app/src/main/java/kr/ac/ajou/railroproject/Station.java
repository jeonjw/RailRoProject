package kr.ac.ajou.railroproject;

import java.util.ArrayList;
import java.util.List;


class Station {
    private String stationName;
    private String date;
    private List<Place> placeList = new ArrayList<Place>();

    public Station() {
    }

    public Station(String stationName, String date) {
        this.stationName = stationName;
        this.date = date;
    }

    public String getStationName() {
        return stationName;
    }

    public List getPlaceList(){
        return placeList;
    }

    public void setPlaceList(List<Place> placeList){
        this.placeList = placeList;
    }

    public String getDate(){
        return date;
    }

    public void addPlace(Place place){
        placeList.add(place);
    }

    public void remove(Place place){
        placeList.remove(place);
    }

}
