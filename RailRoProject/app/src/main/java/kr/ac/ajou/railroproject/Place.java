package kr.ac.ajou.railroproject;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-11-03.
 */

public class Place implements Serializable{

    private String placeName;
    private String placeContent;
    private List<String> imageUrlList = new ArrayList<String>();

    public Place(){}

    public Place(String placeName, String courseContent, List imageUrlList){
        this.placeName = placeName;
        this.placeContent = courseContent;
        this.imageUrlList = imageUrlList;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceContent() {
        return placeContent;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

}
