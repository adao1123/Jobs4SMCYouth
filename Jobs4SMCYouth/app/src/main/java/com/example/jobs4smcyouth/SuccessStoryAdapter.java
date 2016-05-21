package com.example.jobs4smcyouth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adao1 on 5/21/2016.
 */
public class SuccessStoryAdapter extends RecyclerView.Adapter<SuccessStoryAdapter.ViewHolder>{

    ArrayList<String> successStories;

    public SuccessStoryAdapter(ArrayList<String> successStories) {
        this.successStories = successStories;
    }

    @Override
    public SuccessStoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_item_success, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SuccessStoryAdapter.ViewHolder holder, int position) {
        String successStory = successStories.get(position);
        TextView successTV = holder.successTV;
        successTV.setText(successStory);

    }

    @Override
    public int getItemCount() {
        return successStories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView successTV;
        public ViewHolder(View itemView) {
            super(itemView);
            successTV = (TextView)itemView.findViewById(R.id.rv_item_success_text);
        }
    }
}
