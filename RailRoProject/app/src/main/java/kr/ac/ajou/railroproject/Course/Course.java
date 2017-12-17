package kr.ac.ajou.railroproject.Course;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;


public class Course implements Parcelable{
    private String title;
    private int commentCount;
    private int likeCount;
    private int hitCount;
    private String courseKey;
    private String uid;
    private String profileImageURL;
    private List<Station> stationList = new ArrayList<Station>();


    protected Course(Parcel in) {
        title = in.readString();
        commentCount = in.readInt();
        likeCount = in.readInt();
        hitCount = in.readInt();
        courseKey = in.readString();
        uid = in.readString();
        profileImageURL = in.readString();
        stationList = in.readArrayList(null);
    }

    public Course(String title, String UID, List<Station> stationList, String profileImageURL) {
        this.title = title;
        this.uid = UID;
        this.stationList = stationList;
        this.profileImageURL = profileImageURL;
    }

    @Exclude
    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public List<Station> getStationList() {
        return stationList;
    }

    public Course() {
    }

    public void setCourseKey(String courseKey) {
        this.courseKey = courseKey;
    }

    public void setStationList(List<Station> stationList) {
        this.stationList = stationList;
    }

    @Exclude
    public String getCourseKey() {
        return courseKey;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public String getuid(){
        return uid;
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

    public int getHitCount() {
        return hitCount;
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
        hitCount = 0;
        this.uid = UID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(commentCount);
        parcel.writeInt(likeCount);
        parcel.writeInt(hitCount);
        parcel.writeString(courseKey);
        parcel.writeString(uid);
        parcel.writeString(profileImageURL);
        parcel.writeList(stationList);
    }
}
