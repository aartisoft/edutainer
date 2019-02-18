package com.edutainer.in.Model;

/**
 * Created by TalkCharge on 07-03-2018.
 */

public class QuestionModel {
    private String question;
    private String q_type;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correct_answer;
    private String time;
    private String alternate_question;
    private String selection="false";

    public QuestionModel() {

    }

    public QuestionModel(String question, String q_type, String option1, String option2, String option3, String option4, String correct_answer, String time, String selection,String alternate_question) {
        this.question = question;
        this.q_type = q_type;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct_answer = correct_answer;
        this.time = time;
        this.selection = selection;
        this.alternate_question=alternate_question;
    }


    public String getAlternate_question() {
        return alternate_question;
    }

    public void setAlternate_question(String alternate_question) {
        this.alternate_question = alternate_question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQ_type() {
        return q_type;
    }

    public void setQ_type(String q_type) {
        this.q_type = q_type;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
