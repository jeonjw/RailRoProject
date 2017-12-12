package kr.ac.ajou.railroproject.CourseComment;

import com.google.firebase.auth.FirebaseAuth;

public final class CourseComment {

    private final String author;
    private final String text;
    private final long timeStamp;
    private final String uid;

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getUid() {
        return uid;
    }

    public CourseComment() {
        this.author = "";
        this.text = "";
        this.timeStamp = 0;
        this.uid = "";
    }

    private CourseComment(String author, String text) {
        this.author = author;
        this.text = text;
        this.timeStamp = timeStamp();
        this.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    private long timeStamp() { //static 바꾸기
        return System.currentTimeMillis();
    }

    public static CourseComment newComment(String userName, String message) {
        return new CourseComment(userName, message);
    }

}