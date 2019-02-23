package com.edutainer.in.workplace.CourseTopics;

import android.content.Context;

public class TopicPresenterImpl implements TopicContract.TopicPresenter,
    TopicContract.TopicInteraction.OnTopicFinishedListener {

    TopicContract.TopicView topicView;
    TopicContract.TopicInteraction topicInteraction;

    public TopicPresenterImpl(TopicContract.TopicView topicView, TopicContract.TopicInteraction topicInteraction) {
        this.topicView = topicView;
        this.topicInteraction = topicInteraction;
    }

    @Override
    public void topic(Context context, String id) {
        topicInteraction.lessons(context, id, this);
    }

    @Override
    public void onDestroy() {
        topicView = null;
    }

    @Override
    public void onTopicFinished(String string) {
        topicView.handleTopic(string);
    }
}
