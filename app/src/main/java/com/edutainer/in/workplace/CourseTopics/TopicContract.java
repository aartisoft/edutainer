package com.edutainer.in.workplace.CourseTopics;

import android.content.Context;

public class TopicContract {

    interface TopicPresenter{
        void topic(Context context, String id);

        void onDestroy();

    }

    interface TopicView{
        void showProgress();

        void hideProgress();

        void handleTopic(String string);
    }

    interface TopicInteraction{
        interface OnTopicFinishedListener {
            void onTopicFinished(String string);
        }
        void lessons(Context context, String id, OnTopicFinishedListener onTopicFinishedListener);
    }
}

