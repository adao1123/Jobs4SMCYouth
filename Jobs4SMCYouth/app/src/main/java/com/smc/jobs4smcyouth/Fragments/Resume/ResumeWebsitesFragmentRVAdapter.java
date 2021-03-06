package com.smc.jobs4smcyouth.Fragments.Resume;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smc.jobs4smcyouth.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by samsiu on 9/5/16.
 */
public class ResumeWebsitesFragmentRVAdapter extends
        RecyclerView.Adapter<ResumeWebsitesFragmentRVAdapter.ResumeSitesViewHolder> {

    private final List<String> urls;
    private final List<String> linksImages;
    private ResumeCardViewClickListener resumeCardViewClickListener;
    Picasso picasso;

    public interface ResumeCardViewClickListener {
        void onResumeCardViewClick(String url);
    }

    class ResumeSitesViewHolder extends RecyclerView.ViewHolder{

        ImageView resumeListImageView;
        TextView resumeListTextView;
        CardView resumeSiteCardView;

        ResumeSitesViewHolder(View itemView){
            super(itemView);
            resumeListImageView = (ImageView)itemView.findViewById(com.smc.jobs4smcyouth.R.id.resumeListFrag_job_imageView);
            resumeListTextView = (TextView)itemView.findViewById(com.smc.jobs4smcyouth.R.id.resumeListFrag_site_textView);
            resumeSiteCardView = (CardView)itemView.findViewById(com.smc.jobs4smcyouth.R.id.resumeSite_cardView);
        }

        private void bind(final ResumeCardViewClickListener resumeCardViewClickListener, final String url){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resumeCardViewClickListener.onResumeCardViewClick(url);
                }
            });
        }
    }


    public ResumeWebsitesFragmentRVAdapter(List<String> urls, List<String> linksImages, ResumeCardViewClickListener resumeCardViewClickListener){
        this.urls = urls;
        this.linksImages = linksImages;
        this.resumeCardViewClickListener = resumeCardViewClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ResumeSitesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(com.smc.jobs4smcyouth.R.layout.rv_item_resume_website, parent, false);
        ResumeSitesViewHolder resumeSitesViewHolder = new ResumeSitesViewHolder(view);


        picasso = Picasso.with(parent.getContext());

        return resumeSitesViewHolder;
    }

    @Override
    public void onBindViewHolder(ResumeSitesViewHolder holder, int position) {

        holder.resumeListTextView.setText(urls.get(position));
        holder.bind(resumeCardViewClickListener, urls.get(position));

        picasso.load(linksImages.get(position))
                .placeholder(R.drawable.icon_websites)
                .error(R.drawable.icon_websites)
                .resize(500, 225)
                .into(holder.resumeListImageView);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }
}
