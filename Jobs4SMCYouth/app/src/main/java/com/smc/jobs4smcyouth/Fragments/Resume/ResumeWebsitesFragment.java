package com.smc.jobs4smcyouth.Fragments.Resume;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smc.jobs4smcyouth.Utilities.WebViewFragment.WebViewFragment;

import java.util.ArrayList;

/**
 * Created by samsiu on 9/5/16.
 */
public class ResumeWebsitesFragment extends Fragment
        implements ResumeWebsitesFragmentRVAdapter.ResumeCardViewClickListener {

    public ResumeWebsitesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(com.smc.jobs4smcyouth.R.layout.fragment_resume_websites, container, false);

        RecyclerView resumeWebsitesListRecyclerView = (RecyclerView)view.findViewById(com.smc.jobs4smcyouth.R.id.resumeWebsiteListFrag_recyclerView);

        ArrayList<String> urls = new ArrayList<>();
        urls.add("Careeronestop.org");
        urls.add("Damngood.com");
        urls.add("Eresumes.com");
        urls.add("Resume-help.org");
        urls.add("Resumehelp.com");
        urls.add("Myfuture.com");
        urls.add("Susanireland.com");
        urls.add("Truecareers.com");
        urls.add("Monster.com");
        urls.add("Quintcareers.com/resres.html");

        ResumeWebsitesFragmentRVAdapter resumeWebsitesFragmentRVAdapter = new ResumeWebsitesFragmentRVAdapter(urls, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        resumeWebsitesListRecyclerView.setLayoutManager(gridLayoutManager);
        resumeWebsitesListRecyclerView.setAdapter(resumeWebsitesFragmentRVAdapter);


        return view;
    }

    @Override
    public void onResumeCardViewClick(String url) {

        Bundle bundle = new Bundle();
        bundle.putString("url", url);

        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(bundle);

        FragmentManager fragmentManager;
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("ResumeSites");
        fragmentTransaction.replace(com.smc.jobs4smcyouth.R.id.fragment_container_id, webViewFragment);
        fragmentTransaction.commit();



    }
}
