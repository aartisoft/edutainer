package com.edutainer.in.Model;

import java.util.ArrayList;

public class HomeCategoryModel
{
    private String cat_name;
    private String color;
    private ArrayList<CategoryModel> categoryModelArrayList;

    public HomeCategoryModel()
    {

    }

    public HomeCategoryModel(String cat_name, String color,ArrayList<CategoryModel> categoryModelArrayList) {
        this.cat_name = cat_name;
        this.color=color;
        this.categoryModelArrayList = categoryModelArrayList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public ArrayList<CategoryModel> getCategoryModelArrayList() {
        return categoryModelArrayList;
    }

    public void setCategoryModelArrayList(ArrayList<CategoryModel> categoryModelArrayList) {
        this.categoryModelArrayList = categoryModelArrayList;
    }
}
