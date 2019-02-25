package com.edutainer.in.workplace.TopicDoubts;

import android.content.Context;

import com.edutainer.in.workplace.TopicQuestions.QuestionsContract;

public class DoubtPresenterImpl implements DoubtContract.DoubtPresenter,
    DoubtContract.DoubtInteraction.OnDoubtFinishedListener {

    private DoubtContract.DoubtView doubtView;
    private DoubtContract.DoubtInteraction doubtInteraction;

    public DoubtPresenterImpl(DoubtContract.DoubtView doubtView, DoubtContract.DoubtInteraction doubtInteraction) {
        this.doubtView = doubtView;
        this.doubtInteraction = doubtInteraction;
    }

    @Override
    public void Doubt(Context context, String question, String topic_id, String user_id) {
        doubtInteraction.doubt(context, question, topic_id, user_id, this);
    }

    @Override
    public void onDestroy() {
        doubtView = null;
    }

    @Override
    public void onDoubtFinished(String string) {
        doubtView.handleDoubt(string);
    }
}
