package com.edutainer.in.workplace.TopicQuestions;

import android.content.Context;
import com.edutainer.in.workplace.Helper.CommonFunctions;
import com.edutainer.in.workplace.Helper.OkHttpAsyncResponse;

public class GetQuestionsInteractionImpl implements QuestionsContract.QuestionsInteraction{
    @Override
    public void questions(Context context, String id, final OnQuestionsFinishedListener onQuestionsFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.topic_questions(id, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onQuestionsFinishedListener.onQuestionsFinished(output);
                }
            });
        }else {
            onQuestionsFinishedListener.onQuestionsFinished("No network");
        }
    }
}
