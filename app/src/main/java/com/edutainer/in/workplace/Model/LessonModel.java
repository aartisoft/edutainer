package com.edutainer.in.workplace.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class LessonModel implements Parcelable {
    private String id;
    private String course_id;
    private String name;
    private String lesson_detail;

    public LessonModel(String id, String course_id, String name, String lesson_detail) {
        this.id = id;
        this.course_id = course_id;
        this.name = name;
        this.lesson_detail = lesson_detail;
    }

    public LessonModel() {
    }


    protected LessonModel(Parcel in) {
        id = in.readString();
        course_id = in.readString();
        name = in.readString();
        lesson_detail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(course_id);
        dest.writeString(name);
        dest.writeString(lesson_detail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LessonModel> CREATOR = new Creator<LessonModel>() {
        @Override
        public LessonModel createFromParcel(Parcel in) {
            return new LessonModel(in);
        }

        @Override
        public LessonModel[] newArray(int size) {
            return new LessonModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getName() {
        return name;
    }

    public String getLesson_detail() {
        return lesson_detail;
    }
}
