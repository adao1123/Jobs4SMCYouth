package com.example.jobs4smcyouth.Fragments.Resume;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobs4smcyouth.R;
import com.example.jobs4smcyouth.Utilities.TouchImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by samsiu on 5/21/16.
 */
public class ResumeFragmentRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int RESUMEWEBSITES = 0;
    private final List<String> links;
    Picasso picasso;

    ResumeClickListener resumeClickListener;

    public interface ResumeClickListener{
        void ClickListener();
    }

    class ResumeWebsitesViewHolder extends RecyclerView.ViewHolder{

        ResumeWebsitesViewHolder(View itemView){
            super(itemView);
        }

        public void bind(final ResumeClickListener resumeClickListener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resumeClickListener.ClickListener();
                }
            });
        }
    }


    class ResumeViewHolder extends RecyclerView.ViewHolder{

        TouchImageView templateImageView;

        ResumeViewHolder(View itemView){
            super(itemView);
            templateImageView = (TouchImageView)itemView.findViewById(R.id.resumeFrag_template_imageView);
        }
    }

    public ResumeFragmentRVAdapter(List<String> links, ResumeClickListener resumeClickListener){
        this.links = links;
        this.resumeClickListener = resumeClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == RESUMEWEBSITES){
            return RESUMEWEBSITES;
        }else{
            return position;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == RESUMEWEBSITES){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_resume, parent, false);
            ResumeWebsitesViewHolder resumeWebsitesViewHolder = new ResumeWebsitesViewHolder(view);
            return resumeWebsitesViewHolder;

        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_resume_two, parent, false);
            ResumeViewHolder resumeViewHolder = new ResumeViewHolder(view);

            picasso = Picasso.with(parent.getContext());

            return resumeViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);

        if(itemType == RESUMEWEBSITES){
            ((ResumeWebsitesViewHolder)holder).bind(resumeClickListener);
        }else{
            picasso.load(links.get(position))  // Load image from URL
                    .resize(1000, 1400)                        // Resize Image
                    .into(((ResumeViewHolder)holder).templateImageView);      // Load image to view
        }

    }

    @Override
    public int getItemCount() {
        return links.size();
    }



}