package com.smc.jobs4smcyouth.Fragments.Resume;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smc.jobs4smcyouth.MyApplication;
import com.smc.jobs4smcyouth.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResumeFragment extends Fragment implements ResumeFragmentRVAdapter.ResumeClickListener {

    private static final String TAG = ResumeFragment.class.getSimpleName();

    private Tracker analyticsTracker;

    public ResumeFragment() {
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
        analyticsTracker.setScreenName("Screen~" + "ResumeFragment");
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
        View view =  inflater.inflate(R.layout.fragment_resume, container, false);

        RecyclerView resumeRecyclerView = (RecyclerView)view.findViewById(R.id.resumeFrag_recyclerView);

        ArrayList<String> links = new ArrayList<String>();
        links.add("http://i.imgur.com/PfYDfaJ.png"); // CoverLetterTemplate
        links.add("http://i.imgur.com/KUGwFl8.png"); // CoverLetterSample
        links.add("http://i.imgur.com/qYMuZ76.png"); // ResumeSample1;
        links.add("http://i.imgur.com/p658CYz.png"); // ResumeSample2;
        links.add("http://i.imgur.com/5amKaFS.png"); // ResumeSample3
        links.add("http://i.imgur.com/IFApJZX.png"); // SampleReferenceList

        ResumeFragmentRVAdapter resumeRVAdapter = new ResumeFragmentRVAdapter(links, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        resumeRecyclerView.setLayoutManager(linearLayoutManager);
        resumeRecyclerView.setAdapter(resumeRVAdapter);

        return view;
    }

    @Override
    public void ClickListener() {
        Log.d("RESUME", "WEBSITES WAS CLICKED");

        ResumeWebsitesFragment resumeWebsitesFragment = new ResumeWebsitesFragment();

        FragmentManager fragmentManager;
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("ResumeSites");
        fragmentTransaction.replace(R.id.fragment_container_id, resumeWebsitesFragment);
        fragmentTransaction.commit();
    }
}
