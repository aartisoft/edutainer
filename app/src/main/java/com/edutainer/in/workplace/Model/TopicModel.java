package com.edutainer.in.workplace.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class TopicModel implements Parcelable {
    private String id;
    private String name;
    private String content;

    public TopicModel() {
    }

    public TopicModel(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    protected TopicModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TopicModel> CREATOR = new Creator<TopicModel>() {
        @Override
        public TopicModel createFromParcel(Parcel in) {
            return new TopicModel(in);
        }

        @Override
        public TopicModel[] newArray(int size) {
            return new TopicModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
