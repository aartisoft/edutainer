package com.edutainer.in.workplace.CourseTopics;

import android.content.Context;

import com.edutainer.in.workplace.CourseDetails.DetailContract;
import com.edutainer.in.workplace.Helper.CommonFunctions;
import com.edutainer.in.workplace.Helper.OkHttpAsyncResponse;

public class GetTopicInteractionImpl implements TopicContract.TopicInteraction{
    @Override
    public void lessons(Context context, String id, final OnTopicFinishedListener onTopicFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.topic(id, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onTopicFinishedListener.onTopicFinished(output);
                }
            });
        }else {
            onTopicFinishedListener.onTopicFinished("No network");
        }
    }
}
