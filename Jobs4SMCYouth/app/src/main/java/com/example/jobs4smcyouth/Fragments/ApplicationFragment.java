package com.example.jobs4smcyouth.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobs4smcyouth.Adapters.ApplicationFragmentRVAdapter;
import com.example.jobs4smcyouth.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicationFragment extends Fragment {


    public ApplicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application, container, false);

        RecyclerView applicationRecyclerView = (RecyclerView)view.findViewById(R.id.applicationFrag_recyclerView);

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

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        applicationRecyclerView.setItemAnimator(itemAnimator);

        return view;
    }

}
