package com.edutainer.in.workplace.CourseDetails;

import android.content.Context;

public class DetailPresenterImpl implements DetailContract.DetailPresenter,
    DetailContract.DetailInteraction.OnLessonFinishedListener {

    DetailContract.DetailView detailView;
    DetailContract.DetailInteraction detailInteraction;

    public DetailPresenterImpl(DetailContract.DetailView detailView, DetailContract.DetailInteraction detailInteraction) {
        this.detailView = detailView;
        this.detailInteraction = detailInteraction;
    }

    @Override
    public void lessons(Context context, String id) {
        detailInteraction.lessons(context, id, this);
    }

    @Override
    public void onDestroy() {
        detailView = null;
    }

    @Override
    public void onLessonFinished(String string) {
        detailView.handleLesson(string);
    }
}
