package com.example.jobs4smcyouth;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jobs4smcyouth.Utilities.TouchImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by samsiu on 5/21/16.
 */
public class ResumeFragmentRVAdapter extends RecyclerView.Adapter<ResumeFragmentRVAdapter.ResumeViewHolder> {

    private final List<String> links;
    Picasso picasso;

    public static class ResumeViewHolder extends RecyclerView.ViewHolder{

        TouchImageView templateImageView;

        ResumeViewHolder(View itemView){
            super(itemView);
            templateImageView = (TouchImageView)itemView.findViewById(R.id.resumeFrag_template_imageView);
        }
    }

    public ResumeFragmentRVAdapter(List<String> links){
        this.links = links;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ResumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_resume_cardview, parent, false);
        ResumeViewHolder resumeViewHolder = new ResumeViewHolder(view);

        picasso = Picasso.with(parent.getContext());

        return resumeViewHolder;
    }

    @Override
    public void onBindViewHolder(ResumeViewHolder holder, int position) {
        picasso.load(links.get(position))  // Load image from URL
                .resize(1000, 1400)                        // Resize Image
                .into(holder.templateImageView);      // Load image to view
    }

    @Override
    public int getItemCount() {
        return links.size();
    }



}
