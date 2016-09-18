package com.smc.jobs4smcyouth.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smc.jobs4smcyouth.Models.SuccessStory;
import com.smc.jobs4smcyouth.MyApplication;
import com.smc.jobs4smcyouth.R;
import com.smc.jobs4smcyouth.Utilities.ExpandableTextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SuccessStoryFragment extends Fragment {

    private static final String TAG = SuccessStoryFragment.class.getSimpleName();
    Firebase successFb;
    RecyclerView successRV;
    private Tracker analyticsTracker;


    public SuccessStoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        MyApplication application = (MyApplication)getActivity().getApplication();
        analyticsTracker = application.getDefaultTracker();

        sendScreenImageName();

    }

    private void sendScreenImageName(){
        String name = TAG;
        // [START screen_view_hit]
        Log.i(TAG, "Setting Screen Name: " + name);
        analyticsTracker.setScreenName("Screen~"+"SuccessStoryFragment");
        analyticsTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]

        analyticsTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_success_story, container, false);
        successRV = (RecyclerView)view.findViewById(R.id.success_stories_rv_id);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleFirebase();
        initRv();
    }

    private void initRv(){
        successRV.setAdapter(getFbAdapter());
        successRV.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void handleFirebase(){
        Firebase firebaseRef = new Firebase("https://jobs-4-smc-youth.firebaseio.com/");
        successFb = firebaseRef.child("Success Stories");
        successFb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChildren()) addListingsToFb();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private FirebaseRecyclerAdapter getFbAdapter(){
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<SuccessStory,SuccessViewHolder>(SuccessStory.class,R.layout.rv_item_success,SuccessViewHolder.class,successFb){
            @Override
            protected void populateViewHolder(SuccessViewHolder successViewHolder, SuccessStory successStory, int i) {
                successViewHolder.successNameTV.setText(successStory.getName());
                successViewHolder.successStoryTV.setText(successStory.getStory());
                successViewHolder.letterIcon.setLetter(successStory.getName().substring(0,1));
            }
        };
        return adapter;
    }

    public static class SuccessViewHolder extends RecyclerView.ViewHolder{
        public TextView successNameTV;
        public ExpandableTextView successStoryTV;
        public MaterialLetterIcon letterIcon;
        public SuccessViewHolder(View itemView) {
            super(itemView);
            successNameTV = (TextView)itemView.findViewById(R.id.rv_item_success_name);
            successStoryTV = (ExpandableTextView) itemView.findViewById(R.id.rv_item_success_story);
            letterIcon = (MaterialLetterIcon)itemView.findViewById(R.id.success_letter);
        }
    }


    private void addListingsToFb(){
        for (SuccessStory successStory : getStories()){
            successFb.push().setValue(successStory);
        }
    }

    private ArrayList<SuccessStory> getStories(){
        ArrayList<SuccessStory> successStories =  new ArrayList<>();
        for (int i = 0; i < getResources().getStringArray(R.array.success_name).length;i++){
            SuccessStory successStory = new SuccessStory(getResources().getStringArray(R.array.success_name)[i],getResources().getStringArray(R.array.success_story)[i]);
            successStories.add(successStory);
        }
        return successStories;
    }
}
