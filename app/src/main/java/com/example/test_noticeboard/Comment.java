package com.example.test_noticeboard;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.ArrayList;

public class Comment implements Serializable {
    private String c_writer;
    private String c_contents;
    private String c_writer_nickname;
    private String c_like;
    private Timestamp c_timestamp;
    private ArrayList<Comment> c_comments;

    public Comment() {
        this.c_writer = "";
        this.c_contents = "";
        this.c_writer_nickname = "";
        this.c_like = "";
        this.c_timestamp = null;
        this.c_comments = null;
    }

    public Comment(String c_writer, String c_contents, String c_writer_nickname, String c_like, Timestamp c_timestamp, ArrayList<Comment> c_comments) {
        this.c_writer = c_writer;
        this.c_contents = c_contents;
        this.c_writer_nickname = c_writer_nickname;
        this.c_like = c_like;
        this.c_timestamp = c_timestamp;
        this.c_comments = c_comments;
    }

    public String getC_writer() {
        return c_writer;
    }

    public void setC_writer(String c_writer) {
        this.c_writer = c_writer;
    }

    public String getC_contents() {
        return c_contents;
    }

    public void setC_contents(String c_contents) {
        this.c_contents = c_contents;
    }

    public String getC_writer_nickname() {
        return c_writer_nickname;
    }

    public void setC_writer_nickname(String c_writer_nickname) {
        this.c_writer_nickname = c_writer_nickname;
    }

    public String getC_like() {
        return c_like;
    }

    public void setC_like(String c_like) {
        this.c_like = c_like;
    }

    public Timestamp getC_timestamp() {
        return c_timestamp;
    }

    public void setC_timestamp(Timestamp c_timestamp) {
        this.c_timestamp = c_timestamp;
    }

    public ArrayList<Comment> getC_comments() {
        return c_comments;
    }

    public void setC_comments(ArrayList<Comment> c_comments) {
        this.c_comments = c_comments;
    }
}
