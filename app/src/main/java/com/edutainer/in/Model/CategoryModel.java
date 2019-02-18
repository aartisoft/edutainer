package com.edutainer.in.Model;

import android.support.annotation.NonNull;

/**
 * Created by TalkCharge on 08-03-2018.
 */

public class CategoryModel implements Comparable<CategoryModel>
{
    private String id;
    private String cat_name;
    private String active;
    private String instructions;
    private String imageurl;
    private String bannerurl;
    private String isend;
    private String totalquestions;
    private String color;
    private String isonline;
    private String sort;

    public CategoryModel() {

    }

    public CategoryModel(String id, String cat_name, String active, String instructions, String imageurl, String isend) {
        this.id = id;
        this.cat_name = cat_name;
        this.active = active;
        this.instructions = instructions;
        this.imageurl = imageurl;
        this.isend = isend;
    }

    public CategoryModel(String id, String cat_name, String active, String instructions, String imageurl) {
        this.id = id;
        this.cat_name = cat_name;
        this.active = active;
        this.instructions = instructions;
        this.imageurl = imageurl;
    }

    public CategoryModel(String id, String cat_name, String active, String instructions, String imageurl, String isend, String totalquestions,String sort) {
        this.id = id;
        this.cat_name = cat_name;
        this.active = active;
        this.instructions = instructions;
        this.imageurl = imageurl;
        this.isend = isend;
        this.totalquestions = totalquestions;
        this.sort=sort;
    }

    public CategoryModel(String id, String cat_name, String active, String instructions, String imageurl, String isend, String totalquestions, String color,String isonline) {
        this.id = id;
        this.cat_name = cat_name;
        this.active = active;
        this.instructions = instructions;
        this.imageurl = imageurl;
        this.isend = isend;
        this.totalquestions = totalquestions;
        this.color = color;
        this.isonline=isonline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBannerurl() {
        return bannerurl;
    }

    public void setBannerurl(String bannerurl) {
        this.bannerurl = bannerurl;
    }

    public String getIsend() {
        return isend;
    }

    public void setIsend(String isend) {
        this.isend = isend;
    }


    public String getTotalquestions() {
        return totalquestions;
    }

    public void setTotalquestions(String totalquestions) {
        this.totalquestions = totalquestions;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIsonline() {
        return isonline;
    }

    public void setIsonline(String isonline) {
        this.isonline = isonline;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


    @Override
    public int compareTo(@NonNull CategoryModel categoryModel) {
        return this.sort.compareTo(categoryModel.getSort());
    }
}
