package com.edutainer.in.Model;


public class ResultModel {
    private String id;
    private String title;
    private String score;
    private String date;

    public ResultModel() {

    }

    public ResultModel(String id, String title, String score, String date) {
        this.id = id;
        this.title = title;
        this.score = score;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
