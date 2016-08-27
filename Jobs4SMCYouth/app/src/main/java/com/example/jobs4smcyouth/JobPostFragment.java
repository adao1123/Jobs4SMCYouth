package com.example.jobs4smcyouth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jobs4smcyouth.Models.JobListing;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobPostFragment extends Fragment {

    private static final String TAG = "JOB POST FRAGMENT";
    private Firebase firebaseListings;
    private RecyclerView jobListingRV;

    public JobPostFragment() {
        // Required empty public constructor
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
        displayJobListings();
        Log.i(TAG, "onViewCreated: ");
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
            protected void populateViewHolder(JobsViewHolder viewHolder, JobListing jobListing, int i) {
                viewHolder.jobsTitleTV.setText(jobListing.getJobTitle());
                viewHolder.jobsCityTV.setText(jobListing.getJobCity());
                viewHolder.jobsDutiesTV.setText(jobListing.getJobDuty());
                viewHolder.jobsPayrateTV.setText(jobListing.getJobPayrate());
                viewHolder.jobsAgeTV.setText(jobListing.getJobAge());
            }
        };
        jobListingRV.setAdapter(adapter);
        jobListingRV.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    protected static class JobsViewHolder extends RecyclerView.ViewHolder{
        public TextView jobsTitleTV;
        public TextView jobsCityTV;
        public TextView jobsDutiesTV;
        public TextView jobsPayrateTV;
        public TextView jobsAgeTV;
        public JobsViewHolder(View itemView) {
            super(itemView);
            jobsTitleTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_title);
            jobsCityTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_city);
            jobsDutiesTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_duties);
            jobsPayrateTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_payrate);
            jobsAgeTV = (TextView)itemView.findViewById(R.id.rv_item_jobs_age);
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
                    getResources().getStringArray(R.array.age)[i]);
            jobListings.add(jobListing);
        }
        return jobListings;
    }
}
