package com.edutainer.in.Model;

public class EnrollmentModel
{
    private int id;
    private String title;
    private String desc;
    private String img;

    public EnrollmentModel()
    {

    }

    public EnrollmentModel(int id,String title, String desc,String img) {
        this.id=id;
        this.title = title;
        this.desc = desc;
        this.img=img;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "EnrollmentModel{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
