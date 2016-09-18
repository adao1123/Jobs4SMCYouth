package com.smc.jobs4smcyouth.Fragments.Application;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smc.jobs4smcyouth.MyApplication;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicationFragment extends Fragment {
    private static final String TAG = ApplicationFragment.class.getSimpleName();

    private Tracker analyticsTracker;

    public ApplicationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication application = (MyApplication) getActivity().getApplication();
        analyticsTracker = application.getDefaultTracker();
        sendScreenImageName();

    }



    private void sendScreenImageName() {

        String name = TAG;
        // [START screen_view_hit]
        Log.i(TAG, "Setting screen name: " + name);
        analyticsTracker.setScreenName("Screen~" + "ApplicationFragment");
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(com.smc.jobs4smcyouth.R.layout.fragment_application, container, false);

        RecyclerView applicationRecyclerView = (RecyclerView)view.findViewById(com.smc.jobs4smcyouth.R.id.applicationFrag_recyclerView);

        List<String> links = new ArrayList<String>();
        links.add("");
        links.add("http://i.imgur.com/OlLu3bc.png"); // MasterApplication Page 1
        links.add("http://i.imgur.com/SSd9mo2.png"); // MasterApplication Page 2
        links.add("http://i.imgur.com/oNepSHq.png"); // SampleVolunteerExperience1
        links.add("http://i.imgur.com/g4IjG5j.png"); // SampleVolunteerExperience2
        links.add("http://i.imgur.com/0cJNawb.png"); // SampleNoExperience1
        links.add("http://i.imgur.com/QydztcF.png"); // SampleNoExperience2
        links.add("http://i.imgur.com/zoqznjx.png"); // SampleJobExperience1
        links.add("http://i.imgur.com/oKi8Mk4.png"); // SampleJobExperience2

        ApplicationFragmentRVAdapter applicationFragmentRVAdapter = new ApplicationFragmentRVAdapter(links);
        applicationFragmentRVAdapter.notifyItemRangeChanged(0, applicationFragmentRVAdapter.getItemCount());
        applicationRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        applicationRecyclerView.setLayoutManager(linearLayoutManager);
        applicationRecyclerView.setAdapter(applicationFragmentRVAdapter);

//        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//        itemAnimator.setAddDuration(1000);
//        itemAnimator.setRemoveDuration(1000);
//        applicationRecyclerView.setItemAnimator(itemAnimator);

        return view;
    }
}
