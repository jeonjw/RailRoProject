package kr.ac.ajou.railroproject;

import java.util.ArrayList;
import java.util.List;


public class Course {
    private String title;
    private int commentCount;
    private int likeCount;
    private List<Station> stationList;

    public String getTitle() {
        return title;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public Course(String title, int commentCount, int likeCount, List<Station> stationList) {
        this.title = title;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.stationList = stationList;
    }
}
