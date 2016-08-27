package com.example.jobs4smcyouth.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobs4smcyouth.R;
import com.example.jobs4smcyouth.Utilities.TouchImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by samsiu on 7/5/16.
 */
public class ApplicationFragmentRVAdapter extends RecyclerView.Adapter<ApplicationFragmentRVAdapter.ApplicationViewHolder>{


    private final List<String> imageLinks;
    Picasso picasso;

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder{
        TouchImageView touchImageView;

        ApplicationViewHolder(View itemView){
            super(itemView);
            touchImageView = (TouchImageView)itemView.findViewById(R.id.application_touchImageView);
        }
    }

    public ApplicationFragmentRVAdapter(List<String> imageLinks){
        this.imageLinks = imageLinks;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ApplicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_application, parent, false);
        ApplicationViewHolder applicationViewHolder = new ApplicationViewHolder(view);

        picasso = Picasso.with(parent.getContext());

        return applicationViewHolder;
    }

    @Override
    public void onBindViewHolder(ApplicationViewHolder holder, int position) {
        picasso.load(imageLinks.get(position))
                .resize(1000, 1700)
                .into(holder.touchImageView);

    }

    @Override
    public int getItemCount() {
        return imageLinks.size();
    }
}
