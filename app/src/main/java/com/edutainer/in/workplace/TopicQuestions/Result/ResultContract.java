package com.edutainer.in.workplace.TopicQuestions.Result;

import android.content.Context;

public class ResultContract {

    interface ResultPresenter{
        void result(Context context, String topic_id, String course_id, String user_id);

        void onDestroy();

    }

    interface ResultView{
        void showProgress();

        void hideProgress();

        void handleResult(String string);
    }

    interface ResultInteraction{
        interface OnResultFinishedListener {
            void onResultFinished(String string);
        }
        void result(Context context, String topic_id, String course_id, String user_id, OnResultFinishedListener onResultFinishedListener);
    }
}

