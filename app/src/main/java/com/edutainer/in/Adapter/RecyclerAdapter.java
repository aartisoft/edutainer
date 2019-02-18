package com.edutainer.in.Adapter;

/**
 * Created by TalkCharge on 05-09-2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList objectList;
    private int layoutResource;
    public ReturnView return_view;

    public interface ReturnView {
        void returnView(ArrayList objects, int position, View view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View view;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }


    public RecyclerAdapter(ArrayList objectList, int layoutResource, ReturnView return_view) {
        this.objectList = objectList;
        this.layoutResource = layoutResource;
        this.return_view = return_view;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layoutResource, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (return_view != null) {
            return_view.returnView(objectList, position, holder.view);
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}