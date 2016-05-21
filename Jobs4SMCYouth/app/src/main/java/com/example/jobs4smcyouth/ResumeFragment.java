package com.example.jobs4smcyouth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResumeFragment extends Fragment {


    public ResumeFragment() {
        // Required empty public constructor
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

        ResumeFragmentRVAdapter resumeRVAdapter = new ResumeFragmentRVAdapter(links);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        resumeRecyclerView.setLayoutManager(linearLayoutManager);
        resumeRecyclerView.setAdapter(resumeRVAdapter);

        return view;
    }



}
