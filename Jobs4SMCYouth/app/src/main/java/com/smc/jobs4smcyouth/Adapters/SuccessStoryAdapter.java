package com.smc.jobs4smcyouth.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smc.jobs4smcyouth.Models.SuccessStory;
import com.smc.jobs4smcyouth.Utilities.ExpandableTextView;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;

/**
 * Created by adao1 on 5/21/2016.
 */
public class SuccessStoryAdapter extends RecyclerView.Adapter<SuccessStoryAdapter.ViewHolder>{

    ArrayList<SuccessStory> successStories;

    public SuccessStoryAdapter(ArrayList<SuccessStory> successStories) {
        this.successStories = successStories;
    }

    @Override
    public SuccessStoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(com.smc.jobs4smcyouth.R.layout.rv_item_success, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        MaterialLetterIcon icon = new MaterialLetterIcon.Builder(context)
                .letter("S")
                .letterSize(26)
                .lettersNumber(1)
                .create();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SuccessStoryAdapter.ViewHolder holder, int position) {
        SuccessStory successStory = successStories.get(position);
        TextView successNameTV = holder.successNameTV;
        TextView successStoryTV = holder.successStoryTV;
        successNameTV.setText(successStory.getName());
        successStoryTV.setText(successStory.getStory());

    }

    @Override
    public int getItemCount() {
        return successStories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView successNameTV;
        public ExpandableTextView successStoryTV;
        public ViewHolder(View itemView) {
            super(itemView);
            successNameTV = (TextView)itemView.findViewById(com.smc.jobs4smcyouth.R.id.rv_item_success_name);
            successStoryTV = (ExpandableTextView) itemView.findViewById(com.smc.jobs4smcyouth.R.id.rv_item_success_story);
        }
    }
}
