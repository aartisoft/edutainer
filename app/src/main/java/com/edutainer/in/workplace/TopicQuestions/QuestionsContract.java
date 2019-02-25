package com.edutainer.in.workplace.TopicQuestions;

import android.content.Context;

public class QuestionsContract {

    interface QuestionsPresenter{
        void Questions(Context context, String id);

        void onDestroy();

    }

    interface QuestionsView{
        void showProgress();

        void hideProgress();

        void handleQuestions(String string);
    }

    interface QuestionsInteraction{
        interface OnQuestionsFinishedListener {
            void onQuestionsFinished(String string);
        }
        void questions(Context context, String id, OnQuestionsFinishedListener onQuestionsFinishedListener);
    }
}

