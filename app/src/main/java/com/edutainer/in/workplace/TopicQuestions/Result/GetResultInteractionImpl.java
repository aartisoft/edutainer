package com.edutainer.in.workplace.TopicQuestions.Result;

import android.content.Context;

import com.edutainer.in.workplace.Helper.CommonFunctions;
import com.edutainer.in.workplace.Helper.OkHttpAsyncResponse;
import com.edutainer.in.workplace.TopicQuestions.QuestionsContract;

public class GetResultInteractionImpl implements ResultContract.ResultInteraction{
    @Override
    public void result(Context context, String topic_id, String course_id, String user_id, final OnResultFinishedListener onResultFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.quiz(topic_id, course_id, user_id, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onResultFinishedListener.onResultFinished(output);
                }
            });
        }else {
            onResultFinishedListener.onResultFinished("No network");
        }
    }
}
