package com.example.jobs4smcyouth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SuccessStoryFragment extends Fragment {

    RecyclerView successRV;

    public SuccessStoryFragment() {
        // Required empty public constructor
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
        SuccessStoryAdapter successStoryAdapter = new SuccessStoryAdapter(initStories());
        successRV.setAdapter(successStoryAdapter);
        successRV.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ArrayList<String> initStories(){
        return null;
    }
}
