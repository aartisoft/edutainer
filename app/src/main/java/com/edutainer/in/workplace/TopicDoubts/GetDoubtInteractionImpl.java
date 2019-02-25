package com.edutainer.in.workplace.TopicDoubts;

import android.content.Context;

import com.edutainer.in.workplace.Helper.CommonFunctions;
import com.edutainer.in.workplace.Helper.OkHttpAsyncResponse;
import com.edutainer.in.workplace.TopicQuestions.QuestionsContract;

public class GetDoubtInteractionImpl implements DoubtContract.DoubtInteraction{
    @Override
    public void doubt(Context context, String question, String topic_id, String user_id, final OnDoubtFinishedListener onDoubtFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.doubt(question, topic_id, user_id, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onDoubtFinishedListener.onDoubtFinished(output);
                }
            });
        }else {
            onDoubtFinishedListener.onDoubtFinished("No network");
        }
    }
}
