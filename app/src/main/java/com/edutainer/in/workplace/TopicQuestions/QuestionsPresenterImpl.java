package com.edutainer.in.workplace.TopicQuestions;

import android.content.Context;

import com.edutainer.in.workplace.CourseTopics.TopicContract;

public class QuestionsPresenterImpl implements QuestionsContract.QuestionsPresenter,
    QuestionsContract.QuestionsInteraction.OnQuestionsFinishedListener {

    private QuestionsContract.QuestionsView questionsView;
    private QuestionsContract.QuestionsInteraction questionsInteraction;

    public QuestionsPresenterImpl(QuestionsContract.QuestionsView questionsView, QuestionsContract.QuestionsInteraction questionsInteraction) {
        this.questionsView = questionsView;
        this.questionsInteraction = questionsInteraction;
    }

    @Override
    public void Questions(Context context, String id) {
        questionsInteraction.questions(context, id, this);
    }

    @Override
    public void onDestroy() {
        questionsView = null;
    }

    @Override
    public void onQuestionsFinished(String string) {
        questionsView.handleQuestions(string);
    }
}
