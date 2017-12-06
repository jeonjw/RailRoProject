package kr.ac.ajou.railroproject.Board;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Board {
    private final String title;
    private final String contents;
    private final String author;
    private final long timeStamp;
    private final String authorUid;
    private final int commentCount;
    private final List<String> urlList;

    public Board() {
        this.title = "";
        this.contents = "";
        this.author = "";
        this.timeStamp = 0;
        this.authorUid = "";
        this.commentCount = 0;
        this.urlList = null;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorUid() {
        return authorUid;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public List<String> getUrlList() {
        return urlList;
    }


    public Board(String author, String title, String contents, int commentCount, List<String> urlList) {
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.timeStamp = timeStamp();
        this.authorUid = FirebaseAuth.getInstance().getUid();
        this.commentCount = commentCount;
        this.urlList = urlList;
    }

    private long timeStamp() { //static 바꾸기
        return System.currentTimeMillis();
    }

    public static Board newBoard(String userName, String title, String contents, int commentCount) {
        return new Board(userName, title, contents, commentCount, null);
    }

    public static Board newBoardWithImages(String userName, String title, String contents, int commentCount,List<String> urlList) {
        return new Board(userName, title, contents, commentCount, urlList);
    }

}
