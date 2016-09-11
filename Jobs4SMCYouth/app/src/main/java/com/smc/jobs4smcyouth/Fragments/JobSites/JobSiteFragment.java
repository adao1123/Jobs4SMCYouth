package com.smc.jobs4smcyouth.Fragments.JobSites;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smc.jobs4smcyouth.MyApplication;
import com.smc.jobs4smcyouth.Utilities.WebViewFragment.WebViewFragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobSiteFragment extends Fragment implements JobSiteFragmentRVAdapter.OnCardViewClickListener {

    private static final String TAG = JobSiteFragment.class.getSimpleName();

    private Tracker analyticsTracker;

    public JobSiteFragment() {
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
        analyticsTracker.setScreenName("Screen~" + "JobSiteFragment");
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
        View view =  inflater.inflate(com.smc.jobs4smcyouth.R.layout.fragment_job_site, container, false);

        RecyclerView jobListRecyclerView = (RecyclerView)view.findViewById(com.smc.jobs4smcyouth.R.id.jobListFrag_recyclerView);

        ArrayList<String> links = new ArrayList<String>();
        links.add("Acinet.org");
        links.add("Ajb.dni.us");
        links.add("Bajobs.com");
        links.add("Careers.org");
        links.add("Craigslist.org");
        links.add("Bayareacareers.com");
        links.add("Caljobs.ca.gov");
        links.add("Careerbuilder.com");
        links.add("Experience.com");
        links.add("Glassdoor.com");
        links.add("Idealist.org");
        links.add("Indeed.com");
        links.add("Jobhuntersbible.com");
        links.add("Jobstar.org");
        links.add("Monster.com");
        links.add("Simplyhired.com");
        links.add("Opportunitynocs.org");
        links.add("Snagajob.com");
        links.add("Startuphire.com");
        links.add("Calopps.org");


        ArrayList<Integer> linksImage = new ArrayList<>();
        linksImage.add




        JobSiteFragmentRVAdapter jobSiteRVAdapter = new JobSiteFragmentRVAdapter(links, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        jobListRecyclerView.setLayoutManager(gridLayoutManager);
        jobListRecyclerView.setAdapter(jobSiteRVAdapter);

        return view;
    }

    @Override
    public void onCardViewClick(String link) {
        Log.d("JobSiteFragment", "Link returned from Adapter: " + link);

        Bundle bundle = new Bundle();
        bundle.putString("url",link);

        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(bundle);

        FragmentManager fragmentManager;
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("Jobs");
        fragmentTransaction.replace(com.smc.jobs4smcyouth.R.id.fragment_container_id, webViewFragment);
        fragmentTransaction.commit();

    }
}
