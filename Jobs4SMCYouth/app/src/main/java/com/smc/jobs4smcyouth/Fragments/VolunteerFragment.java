package com.smc.jobs4smcyouth.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;
import com.smc.jobs4smcyouth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolunteerFragment extends Fragment {
    private static final String TAG = "VOLUNTEER POST FRAGMENT";
    private Firebase firebaseListings;
    private RecyclerView volunteerListingRV;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;


    public VolunteerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volunteer, container, false);
    }

}
