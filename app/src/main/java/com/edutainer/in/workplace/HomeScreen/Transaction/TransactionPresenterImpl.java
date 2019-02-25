package com.edutainer.in.workplace.HomeScreen.Transaction;

import android.content.Context;

import com.edutainer.in.workplace.HomeScreen.HomeContract;

public class TransactionPresenterImpl implements TransactionContract.TransactionPresenter,
        TransactionContract.TransactionInteraction.OnTransactionFinishedListener
{

    private TransactionContract.TransactionView transactionView;
    private TransactionContract.TransactionInteraction transactionInteraction;

    public TransactionPresenterImpl(TransactionContract.TransactionView transactionView, TransactionContract.TransactionInteraction transactionInteraction) {
        this.transactionView = transactionView;
        this.transactionInteraction = transactionInteraction;
    }

    @Override
    public void transaction(Context context, String user_id) {
        transactionInteraction.transaction(context, user_id, this);
    }

    @Override
    public void onDestroy() {
        transactionView = null;
    }

    @Override
    public void onTransactionFinished(String string) {
        transactionView.handleTransaction(string);
    }
}
