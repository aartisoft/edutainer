package com.edutainer.in.Model;

public class CourseModel {
    private int id;
    private String course_name;
    private String course_preview;
    private int cat_id;
    private String cat_name;
    private String web_image;
    private String course_cost;
    private String course_duration;
    private String course_term;
    private String status;
    private String course_short_desc;
    private String course_kit;

    public CourseModel() {

    }

    public CourseModel(int id, String course_name, String course_preview, int cat_id, String cat_name, String status, String course_short_desc, String course_kit) {
        this.id = id;
        this.course_name = course_name;
        this.course_preview = course_preview;
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.status = status;
        this.course_short_desc = course_short_desc;
        this.course_kit = course_kit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_preview() {
        return course_preview;
    }

    public void setCourse_preview(String course_preview) {
        this.course_preview = course_preview;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getWeb_image() {
        return web_image;
    }

    public void setWeb_image(String web_image) {
        this.web_image = web_image;
    }

    public String getCourse_cost() {
        return course_cost;
    }

    public void setCourse_cost(String course_cost) {
        this.course_cost = course_cost;
    }

    public String getCourse_duration() {
        return course_duration;
    }

    public void setCourse_duration(String course_duration) {
        this.course_duration = course_duration;
    }

    public String getCourse_term() {
        return course_term;
    }

    public void setCourse_term(String course_term) {
        this.course_term = course_term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourse_short_desc() {
        return course_short_desc;
    }

    public void setCourse_short_desc(String course_short_desc) {
        this.course_short_desc = course_short_desc;
    }

    public String getCourse_kit() {
        return course_kit;
    }

    public void setCourse_kit(String course_kit) {
        this.course_kit = course_kit;
    }
}
