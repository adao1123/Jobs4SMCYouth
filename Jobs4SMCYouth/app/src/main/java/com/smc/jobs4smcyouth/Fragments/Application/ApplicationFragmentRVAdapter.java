package com.smc.jobs4smcyouth.Fragments.Application;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.print.PrintHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smc.jobs4smcyouth.R;
import com.smc.jobs4smcyouth.Utilities.EventBus.ApplicationRulesClickEvent;
import com.smc.jobs4smcyouth.Utilities.EventBus.MainBus;
import com.smc.jobs4smcyouth.Utilities.TouchImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samsiu on 7/5/16.
 */
public class ApplicationFragmentRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<String> imageLinks;
    Picasso picasso;

    PrintHelper photoPrinter;
    Resources resources;

    List<Integer> drawablesArrayList;
    List<String> imageNamesList;

    class ApplicationRulesViewHolder extends RecyclerView.ViewHolder{

        LinearLayout rulesView;
        TextView titleTextView;

        ApplicationRulesViewHolder(View itemView){
            super(itemView);

            rulesView = (LinearLayout)itemView.findViewById(R.id.application_rulesView);
            titleTextView = (TextView)itemView.findViewById(R.id.application_rulesTitle_tV_id);

        }
        public void bind(){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("RE", "CLICKED");
                    //applicationOneClickListener.onApplicationOneClick();
                   // MainBus.getInstance().post(new ApplicationRulesClickEvent(view));

                    if(titleTextView.getVisibility() == View.VISIBLE){
                        rulesView.setVisibility(View.VISIBLE);
                        titleTextView.setVisibility(View.GONE);
                    }else{
                        rulesView.setVisibility(View.GONE);
                        titleTextView.setVisibility(View.VISIBLE);
                    }
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

                photoPrinter = new PrintHelper(parent.getContext());
                resources = parent.getContext().getResources();
                createDrawablesList();
                createNamesList();
                return new ApplicationViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
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
            ((ApplicationViewHolder)holder).touchImageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
                    Bitmap bitmap = BitmapFactory.decodeResource(
                            resources,
                            drawablesArrayList.get(position));
                    photoPrinter.printBitmap("Printing " + imageNamesList.get(position), bitmap);
                    return true;
                }
            });



        }
    }

    @Override
    public int getItemCount() {
        return imageLinks.size();
    }


    private void createDrawablesList(){
        drawablesArrayList = new ArrayList<Integer>();
        drawablesArrayList.add(R.drawable.application_master1);
        drawablesArrayList.add(R.drawable.application_master2);
        drawablesArrayList.add(R.drawable.application_volunteer1);
        drawablesArrayList.add(R.drawable.application_volunteer2);
        drawablesArrayList.add(R.drawable.application_nojobexp1);
        drawablesArrayList.add(R.drawable.application_nojobexp2);
        drawablesArrayList.add(R.drawable.application_jobexp1);
        drawablesArrayList.add(R.drawable.application_jobexp2);
    }

    private void createNamesList(){
        imageNamesList = new ArrayList<String>();
        imageNamesList.add("Master Application Sample 1");
        imageNamesList.add("Master Applicatio Sample 2");
        imageNamesList.add("Volunteer Experience Sample 1");
        imageNamesList.add("Volunteer Experience Sample 2");
        imageNamesList.add("Job Application No Experience Sample 1");
        imageNamesList.add("Job Application No Experience Sample 2");
        imageNamesList.add("Job Application with Experience Sample 1");
        imageNamesList.add("Job Application with Experience Sample 2");

    }

}
