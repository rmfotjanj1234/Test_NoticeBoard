package com.example.test_noticeboard;

import com.google.firebase.Timestamp;

import java.util.ArrayList;

public class Post implements Comparable<Post> {
    private String writer;
    private String title;
    private String contents;
    private String writer_nickname;
    private int like;
    private int clcik;
    private Timestamp timestamp;
    private ArrayList<Comment> comments;

    public Post() {
        this.writer = "";
        this.title = "";
        this.contents = "";
        this.writer_nickname = "";
        this.like = 0;
        this.clcik = 0;
        this.timestamp = null;
        this.comments =null ;
    }

    public Post(String writer, String title, String contents, String writer_nickname, int like, int clcik, Timestamp timestamp, ArrayList<Comment> comments) {
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
        return o.getLike() - getLike();
    }
}
