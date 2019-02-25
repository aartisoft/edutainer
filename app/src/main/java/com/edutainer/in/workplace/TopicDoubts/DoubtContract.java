package com.edutainer.in.workplace.TopicDoubts;

import android.content.Context;

public class DoubtContract {

    interface DoubtPresenter{
        void Doubt(Context context, String question, String topic_id, String user_id);

        void onDestroy();

    }

    interface DoubtView{
        void showProgress();

        void hideProgress();

        void handleDoubt(String string);
    }

    interface DoubtInteraction{
        interface OnDoubtFinishedListener {
            void onDoubtFinished(String string);
        }
        void doubt(Context context, String question, String topic_id, String user_id, OnDoubtFinishedListener onDoubtFinishedListener);
    }
}

