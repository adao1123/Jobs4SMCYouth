package com.smc.jobs4smcyouth.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smc.jobs4smcyouth.Models.JobListing;
import com.smc.jobs4smcyouth.MyApplication;
import com.smc.jobs4smcyouth.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobPostFragment extends Fragment {

    private static final String TAG = "JOB POST FRAGMENT";
    private Firebase firebaseListings;
    private RecyclerView jobListingRV;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    private Tracker analyticsTracker;

    public JobPostFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        MyApplication application = (MyApplication) getActivity().getApplication();
        analyticsTracker = application.getDefaultTracker();
        sendScreenImageName();
    }

    private void sendScreenImageName(){
        String name = TAG;
        // [START screen_view_hit]
        Log.i(TAG, "Setting screen name: " + name);
        analyticsTracker.setScreenName("Screen~" + "AboutFragment");
        analyticsTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]

        analyticsTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_job_post, container, false);
        jobListingRV = (RecyclerView)view.findViewById(R.id.job_post_rv_id);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleFirebase();
        createLayoutManagers();
        displayJobListings();
        Log.i(TAG, "onViewCreated: ");
    }

    private void createLayoutManagers(){
        linearLayoutManager = new LinearLayoutManager(getContext());
        gridLayoutManager = new GridLayoutManager(getContext(),2);
    }

    private void handleFirebase(){
        Firebase firebaseRef = new Firebase("https://jobs-4-smc-youth.firebaseio.com/");
        firebaseListings = firebaseRef.child("Job Listings");
        firebaseListings.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChildren()) addListingsToFb();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void displayJobListings(){
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<JobListing,JobsViewHolder>(JobListing.class,R.layout.rv_item_jobs,JobsViewHolder.class,firebaseListings) {
            @Override
            protected void populateViewHolder(final JobsViewHolder viewHolder, JobListing jobListing, int i) {
                viewHolder.jobsTitleTV.setText(jobListing.getJobTitle());
                viewHolder.jobsCityTV.setText(jobListing.getJobCity());
                viewHolder.jobsDutiesTV.setText(jobListing.getJobDuty());
                viewHolder.jobsPayrateTV.setText(jobListing.getJobPayrate());
                viewHolder.jobsAgeTV.setText(jobListing.getJobAge());
                Picasso.with(getContext()).load(jobListing.getJobImageLink())
                        .resize(200,200).centerCrop().error(R.drawable.ic_support).into(viewHolder.jobsImage);
                viewHolder.jobsListingView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(viewHolder.dutyView.getVisibility()==View.VISIBLE){
                            viewHolder.dutyView.setVisibility(View.GONE);
                            viewHolder.ageView.setVisibility(View.GONE);
                            viewHolder.payrateView.setVisibility(View.GONE);
                            viewHolder.cityView.setVisibility(View.GONE);
                            viewHolder.jobsTitleWord.setVisibility(View.GONE);
                            viewHolder.jobsTitleTV.setTextSize(20);
                            viewHolder.jobsTitleTV.setTypeface(Typeface.DEFAULT_BOLD);
                            viewHolder.jobsTitleTV.setTextColor(getContext().getResources().getColor(R.color.card_view_title));
                            viewHolder.jobsImage.setVisibility(View.VISIBLE);
                        }else{
                            viewHolder.jobsImage.setVisibility(View.GONE);
                            viewHolder.dutyView.setVisibility(View.VISIBLE);
                            viewHolder.ageView.setVisibility(View.VISIBLE);
                            viewHolder.payrateView.setVisibility(View.VISIBLE);
                            viewHolder.jobsTitleWord.setVisibility(View.VISIBLE);
                            viewHolder.cityView.setVisibility(View.VISIBLE);
                            viewHolder.jobsTitleTV.setTextSize(15);
                            viewHolder.jobsTitleTV.setTypeface(Typeface.DEFAULT);
                            viewHolder.jobsTitleTV.setTextColor(getContext().getResources().getColor(android.R.color.tab_indicator_text));
                        }
                    }
                });
            }
        };
        jobListingRV.setAdapter(adapter);
        jobListingRV.setLayoutManager(linearLayoutManager);
    }

    protected static class JobsViewHolder extends RecyclerView.ViewHolder{
        public TextView jobsTitleTV;
        public TextView jobsCityTV;
        public TextView jobsDutiesTV;
        public TextView jobsPayrateTV;
        public TextView jobsAgeTV;
        public TextView jobsTitleWord;
        public RoundedImageView jobsImage;
        public LinearLayout jobsListingView;
        public LinearLayout titleView;
        public LinearLayout cityView;
        public LinearLayout dutyView;
        public LinearLayout payrateView;
        public LinearLayout ageView;
        public JobsViewHolder(View itemView) {
            super(itemView);
            jobsImage = (RoundedImageView)itemView.findViewById(R.id.rv_item_jobs_image);
            jobsTitleTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_title);
            jobsCityTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_city);
            jobsDutiesTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_duties);
            jobsPayrateTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_payrate);
            jobsAgeTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_age);
            jobsTitleWord = (TextView)itemView.findViewById(R.id.jobs_title_text);
            titleView = (LinearLayout)itemView.findViewById(R.id.jobs_title_view);
            cityView = (LinearLayout)itemView.findViewById(R.id.jobs_city_view);
            payrateView = (LinearLayout)itemView.findViewById(R.id.jobs_payrate_view);
            ageView = (LinearLayout)itemView.findViewById(R.id.jobs_age_view);
            dutyView = (LinearLayout)itemView.findViewById(R.id.jobs_duties_view);
            jobsListingView = (LinearLayout)itemView.findViewById(R.id.jobs_listing);
        }
    }

    private void addListingsToFb(){
        for (JobListing jobListing : makeJobListings()){
            firebaseListings.push().setValue(jobListing);
        }
    }

    private ArrayList<JobListing> makeJobListings(){
        ArrayList<JobListing> jobListings = new ArrayList<>();
        for (int i = 0; i<getResources().getStringArray(R.array.jobTitle).length;i++){
            JobListing jobListing = new JobListing(getResources().getStringArray(R.array.jobTitle)[i],
                    getResources().getStringArray(R.array.city)[i],
                    getResources().getStringArray(R.array.duties)[i],
                    getResources().getStringArray(R.array.payRate)[i],
                    getResources().getStringArray(R.array.age)[i],
                    "http://www.tarleton.edu/applications/press/files/3452.JPG");
            jobListings.add(jobListing);
        }
        return jobListings;
    }
}
