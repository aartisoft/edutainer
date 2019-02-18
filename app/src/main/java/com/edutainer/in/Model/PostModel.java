package com.edutainer.in.Model;

import android.support.annotation.NonNull;

public class PostModel implements Comparable<PostModel> {
    private String title;
    private String img;
    private String description;
    private int sort_id;

    public PostModel() {

    }

    public PostModel(String title, String img, String description, int sort_id) {
        this.title = title;
        this.img = img;
        this.description = description;
        this.sort_id = sort_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    @Override
    public int compareTo(@NonNull PostModel postModel) {
        Integer integer=new Integer(this.sort_id);
        Integer integer1=new Integer(postModel.getSort_id());
        return integer.compareTo(integer1);
    }
}
