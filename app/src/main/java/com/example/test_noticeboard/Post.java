package com.example.test_noticeboard;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {
    private String postID;
    private String writer;
    private String title;
    private String contents;
    private String writer_nickname;
    private int like;
    private int clcik;
    private long timestamp;
    private ArrayList<Comment> comments;

    public Post() {
        this.postID ="";
        this.writer = "";
        this.title = "";
        this.contents = "";
        this.writer_nickname = "";
        this.like = 0;
        this.clcik = 0;
        this.timestamp = 0;
        this.comments =null ;
    }

    public Post(String postID, String writer, String title, String contents, String writer_nickname, int like, int clcik, long timestamp, ArrayList<Comment> comments) {
        this.postID = postID;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.writer_nickname = writer_nickname;
        this.like = like;
        this.clcik = clcik;
        this.timestamp = timestamp;
        this.comments = comments;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWriter_nickname() {
        return writer_nickname;
    }

    public void setWriter_nickname(String writer_nickname) {
        this.writer_nickname = writer_nickname;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getClcik() {
        return clcik;
    }

    public void setClcik(int clcik) {
        this.clcik = clcik;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
