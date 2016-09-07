package com.example.jobs4smcyouth.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobs4smcyouth.Models.Scholarship;
import com.example.jobs4smcyouth.R;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScholarshipFragment extends Fragment {

    private RecyclerView scholarshipRv;
    private Firebase scholarshipFb;

    public ScholarshipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scholarship, container, false);
        scholarshipRv = (RecyclerView)view.findViewById(R.id.scholarship_rv);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFirebase();
    }

    private void initFirebase(){
        Firebase firebaseRef = new Firebase("https://jobs-4-smc-youth.firebaseio.com/");
        scholarshipFb = firebaseRef.child("Scholarships");
    }

    private void initRV(){
        scholarshipRv.setLayoutManager(new LinearLayoutManager(getContext()));
        scholarshipRv.setAdapter(getFbRvAdapter());
    }

    private FirebaseRecyclerAdapter getFbRvAdapter(){
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Scholarship,ScholarshipViewHolder>(Scholarship.class,R.layout.rv_item_jobs,ScholarshipViewHolder.class,scholarshipFb){
            @Override
            protected void populateViewHolder(ScholarshipViewHolder scholarshipViewHolder, Scholarship scholarship, int i) {

            }
        };
        return adapter;
    }

    protected static class ScholarshipViewHolder extends RecyclerView.ViewHolder{
        public ScholarshipViewHolder(View itemView) {
            super(itemView);
        }
    }
}
