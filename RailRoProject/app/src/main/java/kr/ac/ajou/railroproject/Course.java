package kr.ac.ajou.railroproject;

import java.util.ArrayList;
import java.util.List;


public class Course {
    private String title;
    private int commentCount;
    private int likeCount;
    private String UID;
    private List<Station> stationList = new ArrayList<Station>();

    public List<Station> getStationList() {
        return stationList;
    }

    public Course() {
    }

    public String getUID(){
        return UID;
    }

    public String getTitle() {
        return title;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void addStation(Station station){
        stationList.add(station);
    }

    public void removeStation(Station station){
        stationList.remove(station);
    }

    public Course(String title, int commentCount, int likeCount, String UID) {
        this.title = title;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.UID = UID;
    }
}
