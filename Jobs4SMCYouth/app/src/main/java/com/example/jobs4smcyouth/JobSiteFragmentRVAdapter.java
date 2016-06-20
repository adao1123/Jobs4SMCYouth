package com.example.jobs4smcyouth;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private final OnCardViewClickListener clickListener;
    Picasso picasso;

    public interface OnCardViewClickListener{
        void onCardViewClick(String link);
    }


    public static class JobSiteViewHolder extends RecyclerView.ViewHolder{

        ImageView jobListImageView;
        TextView jobListTextView;
        CardView jobSiteCardView;

        JobSiteViewHolder(View itemView){
            super(itemView);
            jobListImageView = (ImageView)itemView.findViewById(R.id.jobListFrag_job_imageView);
            jobListTextView = (TextView)itemView.findViewById(R.id.jobListFrag_job_textView);
            jobSiteCardView = (CardView)itemView.findViewById(R.id.jobSite_cardView);
        }
        public void bind(final OnCardViewClickListener clickListener, final String link){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("JobSiteRVAdapter", "Clicked on a link " + link);
                    clickListener.onCardViewClick(link);
                }
            });
        }
    }

    public JobSiteFragmentRVAdapter(List<String> links, OnCardViewClickListener clickListener){
        this.links = links;
        this.clickListener = clickListener;
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
    public void onBindViewHolder(JobSiteViewHolder holder, final int position) {
        Log.d("JobSiteRVAdapter", "OnBindViewHolder items");
        holder.jobListTextView.setText(links.get(position));

        //holder.jobListImageView.setImageResource(R.drawable.logo_careerbuilder);

        holder.bind(clickListener, links.get(position));

//        picasso.load(links.get(position))  // Load image from URL
//                .resize(1000, 1400)                        // Resize Image
//                .into(holder.templateImageView);      // Load image to view
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

}