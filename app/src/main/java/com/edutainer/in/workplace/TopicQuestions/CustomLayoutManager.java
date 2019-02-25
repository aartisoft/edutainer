package com.edutainer.in.workplace.TopicQuestions;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class CustomLayoutManager extends LinearLayoutManager {
        private boolean isVerticalScrollEnabled = true;
        private boolean isHorizontalScrollEnabled = true;

        public CustomLayoutManager(Context context) {
            super(context);
        }

        public void setHorizontalScrollEnabled(boolean flag) {
            this.isHorizontalScrollEnabled = flag;
        }

        public void setVerticalScrollEnabled(boolean flag) {
            this.isVerticalScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
            return isVerticalScrollEnabled && super.canScrollVertically();
        }
        @Override
        public boolean canScrollHorizontally() {
            //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
            return isHorizontalScrollEnabled && super.canScrollHorizontally();
        }
}
