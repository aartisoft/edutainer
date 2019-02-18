package com.edutainer.in.workplace.CourseDetails;

import android.content.Context;

import com.edutainer.in.workplace.Helper.CommonFunctions;
import com.edutainer.in.workplace.Helper.OkHttpAsyncResponse;

public class GetDetailInteractionImpl implements DetailContract.DetailInteraction{
    @Override
    public void lessons(Context context, String id, final OnLessonFinishedListener onLessonFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.loadLesson(id, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onLessonFinishedListener.onLessonFinished(output);
                }
            });
        }else {
            onLessonFinishedListener.onLessonFinished("No network");
        }
    }
}
