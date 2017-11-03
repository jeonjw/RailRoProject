package kr.ac.ajou.railroproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-11-03.
 */

public class Place {

    private String placeName;
    private String courseContent;
    private String date;
    private List<String> imageUrlList = new ArrayList<String>();

    public Place(){}

    public Place(String placeName, String courseContent, String date){
        this.placeName = placeName;
        this.courseContent = courseContent;
        this.date = date;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public String getDate() {
        return date;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void addImageUrl(String url){
        imageUrlList.add(url);
    }
}
