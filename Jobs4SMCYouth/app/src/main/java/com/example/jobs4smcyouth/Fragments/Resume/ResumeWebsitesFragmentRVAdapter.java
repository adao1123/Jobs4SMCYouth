package com.example.jobs4smcyouth.Fragments.Resume;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jobs4smcyouth.R;

import java.util.List;

/**
 * Created by samsiu on 9/5/16.
 */
public class ResumeWebsitesFragmentRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<String> urls;
    private CardViewClickListener cardViewClickListener;


    class ResumeSitesViewHolder extends RecyclerView.ViewHolder{

        ImageView resumeListImageView;
        TextView resumeListTextView;

        ResumeSitesViewHolder(View itemView){
            super(itemView);
            resumeListImageView = (ImageView)itemView.findViewById(R.id.resumeListFrag_job_imageView);
            resumeListTextView = (TextView)itemView.findViewById(R.id.resumeListFrag_site_textView);
        }

        private void bind(final CardViewClickListener cardViewClickListener, final String url){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardViewClickListener.onCardViewClick(url);
                }
            });
        }
    }

    public interface CardViewClickListener{
        void onCardViewClick(String url);
    }

    public ResumeWebsitesFragmentRVAdapter(List<String> urls, CardViewClickListener cardViewClickListener){
        this.urls = urls;
        this.cardViewClickListener = cardViewClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_resume_site, parent, false);
        ResumeSitesViewHolder resumeSitesViewHolder = new ResumeSitesViewHolder(view);
        return resumeSitesViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ResumeSitesViewHolder)holder).resumeListTextView.setText(urls.get(position));
        ((ResumeSitesViewHolder)holder).bind(cardViewClickListener, urls.get(position));
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }
}
