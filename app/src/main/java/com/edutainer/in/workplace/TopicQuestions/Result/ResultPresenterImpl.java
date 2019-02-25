package com.edutainer.in.workplace.TopicQuestions.Result;

import android.content.Context;

import com.edutainer.in.workplace.TopicQuestions.QuestionsContract;

public class ResultPresenterImpl implements ResultContract.ResultPresenter,
    ResultContract.ResultInteraction.OnResultFinishedListener {

    private ResultContract.ResultView resultView;
    private ResultContract.ResultInteraction resultInteraction;

    public ResultPresenterImpl(ResultContract.ResultView resultView, ResultContract.ResultInteraction resultInteraction) {
        this.resultView = resultView;
        this.resultInteraction = resultInteraction;
    }

    @Override
    public void result(Context context, String topic_id, String course_id, String user_id) {
        resultInteraction.result(context, topic_id, course_id, user_id, this);
    }

    @Override
    public void onDestroy() {
        resultView = null;
    }

    @Override
    public void onResultFinished(String string) {
        resultView.handleResult(string);
    }
}
