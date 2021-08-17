package com.example.test_noticeboard;

import com.google.firebase.Timestamp;

import java.util.ArrayList;

public class Post implements Comparable<Post> {
    private String writer;
    private String title;
    private String contents;
    private String writer_nickname;
    private String like;
    private String clcik;
    private Timestamp timestamp;
    private ArrayList<Comment> comments;

    public Post() {
        this.writer = "";
        this.title = "";
        this.contents = "";
        this.writer_nickname = "";
        this.like = "";
        this.clcik = "";
        this.timestamp = null;
        this.comments =null ;
    }

    public Post(String writer, String title, String contents, String writer_nickname, String like, String clcik, Timestamp timestamp, ArrayList<Comment> comments) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.writer_nickname = writer_nickname;
        this.like = like;
        this.clcik = clcik;
        this.timestamp = timestamp;
        this.comments = comments;
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

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getClcik() {
        return clcik;
    }

    public void setClcik(String clcik) {
        this.clcik = clcik;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public int compareTo(Post o) {
        return Integer.parseInt(o.getLike()) - Integer.parseInt(getLike());
    }
}
