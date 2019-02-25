package com.edutainer.in.workplace.HomeScreen.Transaction;

import android.content.Context;

public class TransactionContract {
    interface TransactionPresenter{

        void transaction(Context context, String user_id);

        void onDestroy();

    }

    interface TransactionView{
        void showProgress();

        void hideProgress();

        void handleTransaction(String string);
    }

    interface TransactionInteraction{
        interface OnTransactionFinishedListener {
            void onTransactionFinished(String string);
        }
        void transaction(Context context, String user_id, OnTransactionFinishedListener onTransactionFinishedListener);
    }
}
