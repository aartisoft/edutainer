package com.edutainer.in.workplace.HomeScreen;

public class HomeContract {
    interface HomePresenter{
        void onDestroy();

    }

    interface HomeView{
        void showProgress();

        void hideProgress();
    }

    interface HomeInteraction{
/*
        interface OnLoadModelFinishedListener {
            void onLoadModelFinished(String string);
        }
        void loadModel(Context context,OnLoadModelFinishedListener onLoadModelFinishedListener);
*/
    }
}
