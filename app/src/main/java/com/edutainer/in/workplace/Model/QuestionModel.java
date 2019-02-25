package com.edutainer.in.workplace.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionModel implements Parcelable {
    private String id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correct_answer;
    private String selection="false";

    public QuestionModel() {
    }

    public QuestionModel(String id, String question, String option1, String option2, String option3, String option4, String correct_answer, String selection) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct_answer = correct_answer;
        this.selection = selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getSelection() {
        return selection;
    }

    protected QuestionModel(Parcel in) {
        id = in.readString();
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        correct_answer = in.readString();
        selection = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(correct_answer);
        dest.writeString(selection);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };
}
