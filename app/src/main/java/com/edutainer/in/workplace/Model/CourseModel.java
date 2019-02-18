package com.edutainer.in.workplace.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseModel implements Parcelable {
    private int cat_id;
    private String course_cost;
    private String course_desc;
    private String course_duration;
    private String course_name;
    private String course_period;
    private String course_preview;
    private String course_short_desc;
    private String course_term;
    private int id;
    private String kit_cost;
    private String cat_name;
    private String status;
    private String web_image;

    public CourseModel() {
    }

    public CourseModel(int cat_id, String course_cost, String course_desc, String course_duration, String course_name, String course_period, String course_preview, String course_short_desc, String course_term, int id, String kit_cost, String cat_name, String status, String web_image) {
        this.cat_id = cat_id;
        this.course_cost = course_cost;
        this.course_desc = course_desc;
        this.course_duration = course_duration;
        this.course_name = course_name;
        this.course_period = course_period;
        this.course_preview = course_preview;
        this.course_short_desc = course_short_desc;
        this.course_term = course_term;
        this.id = id;
        this.kit_cost = kit_cost;
        this.cat_name = cat_name;
        this.status = status;
        this.web_image = web_image;
    }

    protected CourseModel(Parcel in) {
        cat_id = in.readInt();
        course_cost = in.readString();
        course_desc = in.readString();
        course_duration = in.readString();
        course_name = in.readString();
        course_period = in.readString();
        course_preview = in.readString();
        course_short_desc = in.readString();
        course_term = in.readString();
        id = in.readInt();
        kit_cost = in.readString();
        cat_name = in.readString();
        status = in.readString();
        web_image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cat_id);
        dest.writeString(course_cost);
        dest.writeString(course_desc);
        dest.writeString(course_duration);
        dest.writeString(course_name);
        dest.writeString(course_period);
        dest.writeString(course_preview);
        dest.writeString(course_short_desc);
        dest.writeString(course_term);
        dest.writeInt(id);
        dest.writeString(kit_cost);
        dest.writeString(cat_name);
        dest.writeString(status);
        dest.writeString(web_image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CourseModel> CREATOR = new Creator<CourseModel>() {
        @Override
        public CourseModel createFromParcel(Parcel in) {
            return new CourseModel(in);
        }

        @Override
        public CourseModel[] newArray(int size) {
            return new CourseModel[size];
        }
    };

    public int getCat_id() {
        return cat_id;
    }

    public String getCourse_cost() {
        return course_cost;
    }

    public String getCourse_desc() {
        return course_desc;
    }

    public String getCourse_duration() {
        return course_duration;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getCourse_period() {
        return course_period;
    }

    public String getCourse_preview() {
        return course_preview;
    }

    public String getCourse_short_desc() {
        return course_short_desc;
    }

    public String getCourse_term() {
        return course_term;
    }

    public int getId() {
        return id;
    }

    public String getKit_cost() {
        return kit_cost;
    }

    public String getCat_name() {
        return cat_name;
    }

    public String getStatus() {
        return status;
    }

    public String getWeb_image() {
        return web_image;
    }
}
