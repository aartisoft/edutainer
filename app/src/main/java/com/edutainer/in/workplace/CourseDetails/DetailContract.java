package com.edutainer.in.workplace.CourseDetails;

import android.content.Context;

public class DetailContract {

    interface DetailPresenter{
        void lessons(Context context, String id);

        void onDestroy();

    }

    interface DetailView{
        void showProgress();

        void hideProgress();

        void handleLesson(String string);
    }

    interface DetailInteraction{
        interface OnLessonFinishedListener {
            void onLessonFinished(String string);
        }
        void lessons(Context context, String id, OnLessonFinishedListener onLessonFinishedListener);
    }
}

