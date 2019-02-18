package com.edutainer.in.Model;

public class LessonModel
{
    private String id;
    private String name;
    private String details;

    public LessonModel()
    {

    }


    public LessonModel(String id, String name,String details) {
        this.id = id;
        this.name = name;
        this.details=details;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
