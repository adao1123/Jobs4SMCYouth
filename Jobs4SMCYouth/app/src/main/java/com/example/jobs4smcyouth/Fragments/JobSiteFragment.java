package com.example.jobs4smcyouth.Fragments;


import android.os.Bundle;
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

import com.example.jobs4smcyouth.Adapters.JobSiteFragmentRVAdapter;
import com.example.jobs4smcyouth.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobSiteFragment extends Fragment implements JobSiteFragmentRVAdapter.OnCardViewClickListener {


    public JobSiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_job_site, container, false);

        RecyclerView jobListRecyclerView = (RecyclerView)view.findViewById(R.id.jobListFrag_recyclerView);

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

        JobSiteWebViewFragment jobSiteWebViewFragment = new JobSiteWebViewFragment();
        jobSiteWebViewFragment.setArguments(bundle);

        FragmentManager fragmentManager;
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_id, jobSiteWebViewFragment);
        fragmentTransaction.commit();

    }
}
