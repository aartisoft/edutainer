package com.edutainer.in.Utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class PlayGifView extends View {
    private int mMovieResourceId;
    private Movie mMovie;

    private long mMovieStart = 0;
    private int mCurrentAnimationTime = 0;

    public PlayGifView(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @SuppressLint("NewApi")
    public PlayGifView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public PlayGifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public void setImageResource(int mvId) {
        if(mMovie!=null)
        {
            mMovie=null;
        }
        this.mMovieResourceId = mvId;
        mCurrentAnimationTime=0;
        mMovieStart=0;
        mMovie = Movie.decodeStream(getResources().openRawResource(mMovieResourceId));
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMovie != null) {
            setMeasuredDimension(mMovie.width(), mMovie.height());
        } else {
            setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMovie != null) {
            updateAnimtionTime(canvas);
        }
    }

    private void updateAnimtionTime(Canvas canvas) {
        long now = android.os.SystemClock.uptimeMillis();

        if (mMovieStart == 0) {
            mMovieStart = now;
        }

        mCurrentAnimationTime = (int) ((now - mMovieStart));
        if (mCurrentAnimationTime > mMovie.duration()) {
            mCurrentAnimationTime = mMovie.duration();
        }

        mMovie.setTime(mCurrentAnimationTime);
        mMovie.draw(canvas, 0, 0);
        if (mCurrentAnimationTime < mMovie.duration()) {
            invalidate();
        }
    }
}