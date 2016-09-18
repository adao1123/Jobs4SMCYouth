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
import com.smc.jobs4smcyouth.R;
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
        links.add("http://www.Acinet.org");
        links.add("http://www.Ajb.dni.us");
        links.add("http://www.Bajobs.com");
        links.add("http://www.Careers.org");
        links.add("http://www.Craigslist.org");
        links.add("http://www.Bayareacareers.com");
        links.add("http://www.Caljobs.ca.gov");
        links.add("http://www.Careerbuilder.com");
        links.add("http://www.Experience.com");
        links.add("http://www.Glassdoor.com");
        links.add("http://www.Idealist.org");
        links.add("http://www.Indeed.com");
        links.add("http://www.Jobhuntersbible.com");
        links.add("http://www.Jobstar.org");
        links.add("http://www.Monster.com");
        links.add("http://www.Simplyhired.com");
        links.add("http://www.Opportunitynocs.org");
        links.add("http://www.Snagajob.com");
        links.add("http://www.Startuphire.com");
        links.add("http://www.Calopps.org");


//        ArrayList<Integer> linksImage = new ArrayList<Integer>();
//        linksImage.add(R.drawable.logo_acinet_full);
//        linksImage.add(0);
//        linksImage.add(R.drawable.logo_bajobs);
//        linksImage.add(R.drawable.logo_careersorg);
//        linksImage.add(R.drawable.logo_craigslist);
//        linksImage.add(R.drawable.logo_bayareajobs);
//        linksImage.add(R.drawable.logo_caljobs);
//        linksImage.add(R.drawable.logo_careerbuilder);
//        linksImage.add(R.drawable.logo_experience);
//        linksImage.add(R.drawable.logo_glassdoor);
//        linksImage.add(R.drawable.logo_idealist);
//        linksImage.add(R.drawable.logo_indeed);
//        linksImage.add(0);
//        linksImage.add(R.drawable.logo_jobstar);
//        linksImage.add(R.drawable.logo_monster);
//        linksImage.add(R.drawable.logo_simplyhired);
//        linksImage.add(0);
//        linksImage.add(R.drawable.logo_snagajob);
//        linksImage.add(R.drawable.logo_startup_hire);
//        linksImage.add(R.drawable.logo_calopps);
//

        ArrayList<String> linksImage = new ArrayList<String>();
        linksImage.add("http://i.imgur.com/WaMTa7X.png"); //acinet
        linksImage.add("http://i.imgur.com/9cxaJf0.png"); // empty
        linksImage.add("http://i.imgur.com/T7pq1AY.png"); //bajobs
        linksImage.add("http://i.imgur.com/RTLSHUL.png"); //careers org
        linksImage.add("http://i.imgur.com/NbwCrXR.png"); //craigslist
        linksImage.add("http://i.imgur.com/v6dPIoi.png"); //bay area jobs
        linksImage.add("http://i.imgur.com/RBoYAlK.png"); //cal jobs
        linksImage.add("http://i.imgur.com/5Whs49g.png"); //career builder
        linksImage.add("http://i.imgur.com/Q9c73bv.png"); //experience
        linksImage.add("http://i.imgur.com/Qr5eQSI.png"); // glassdoor
        linksImage.add("http://i.imgur.com/UtHcmtS.png"); //idealist
        linksImage.add("http://i.imgur.com/6g0gHLJ.png"); //indeed
        linksImage.add("http://i.imgur.com/9cxaJf0.png"); //empty
        linksImage.add("http://i.imgur.com/ufg2jM4.png"); //jobstar
        linksImage.add("http://i.imgur.com/hPysrW1.png"); //monster
        linksImage.add("http://i.imgur.com/uPwztT8.png"); //simply hired
        linksImage.add("http://i.imgur.com/9cxaJf0.png"); //empty
        linksImage.add("http://i.imgur.com/Rigwolq.png"); //snag a job
        linksImage.add("http://i.imgur.com/FcDJory.png"); //startup hire
        linksImage.add("http://i.imgur.com/aRteqkh.png"); //calopps







        JobSiteFragmentRVAdapter jobSiteRVAdapter = new JobSiteFragmentRVAdapter(links, linksImage, this);
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
