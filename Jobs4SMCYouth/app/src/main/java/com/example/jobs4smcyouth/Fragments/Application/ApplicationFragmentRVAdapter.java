package com.example.jobs4smcyouth.Fragments.Application;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobs4smcyouth.R;
import com.example.jobs4smcyouth.Utilities.EventBus.ApplicationRulesClickEvent;
import com.example.jobs4smcyouth.Utilities.EventBus.MainBus;
import com.example.jobs4smcyouth.Utilities.TouchImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by samsiu on 7/5/16.
 */
public class ApplicationFragmentRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<String> imageLinks;
    Picasso picasso;

    class ApplicationRulesViewHolder extends RecyclerView.ViewHolder{
        ApplicationRulesViewHolder(View itemView){
            super(itemView);
        }
        public void bind(){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("RE", "CLICKED");
                    //applicationOneClickListener.onApplicationOneClick();
                    MainBus.getInstance().post(new ApplicationRulesClickEvent(view));
                }
            });
        }

    }

    class ApplicationViewHolder extends RecyclerView.ViewHolder{
        TouchImageView touchImageView;

        ApplicationViewHolder(View itemView){
            super(itemView);
            touchImageView = (TouchImageView)itemView.findViewById(R.id.application_touchImageView);
        }

        public void bindView(int position){
            picasso.load(imageLinks.get(position))
                    .resize(1000, 1700)
                    .into(touchImageView);
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
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }else if(position == 1){
            return 1;
        }else{
            return position;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch(viewType){
            case 0:
                View viewRule = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_application, parent, false);
                return new ApplicationRulesViewHolder(viewRule);
            default:
                picasso = Picasso.with(parent.getContext());
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_application_two, parent, false);
                return new ApplicationViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);
        Log.d("RecyclerView", "Position: "+position);
        if(itemType == 0){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ApplicationRulesViewHolder)holder).bind();
                }
            });
        }
        else{
            ((ApplicationViewHolder)holder).bindView(position);
        }
    }

    @Override
    public int getItemCount() {
        return imageLinks.size();
    }
}
