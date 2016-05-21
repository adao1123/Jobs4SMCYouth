package com.example.jobs4smcyouth;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by samsiu on 5/21/16.
 */
public class JobSiteFragmentRVAdapter extends RecyclerView.Adapter<JobSiteFragmentRVAdapter.JobSiteViewHolder> {

    private final List<String> links;
    Picasso picasso;

    public static class JobSiteViewHolder extends RecyclerView.ViewHolder{

        ImageView jobListImageView;
        TextView jobListTextView;

        JobSiteViewHolder(View itemView){
            super(itemView);
            jobListImageView = (ImageView)itemView.findViewById(R.id.jobListFrag_job_imageView);
            jobListTextView = (TextView)itemView.findViewById(R.id.jobListFrag_job_textView);
        }
    }

    public JobSiteFragmentRVAdapter(List<String> links){
        this.links = links;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public JobSiteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_job_site_cardview, parent, false);
        JobSiteViewHolder jobSiteViewHolder = new JobSiteViewHolder(view);

        picasso = Picasso.with(parent.getContext());

        return jobSiteViewHolder;
    }

    @Override
    public void onBindViewHolder(JobSiteViewHolder holder, int position) {
        holder.jobListTextView.setText(links.get(position));
//        picasso.load(links.get(position))  // Load image from URL
//                .resize(1000, 1400)                        // Resize Image
//                .into(holder.templateImageView);      // Load image to view
    }

    @Override
    public int getItemCount() {
        return links.size();
    }



}