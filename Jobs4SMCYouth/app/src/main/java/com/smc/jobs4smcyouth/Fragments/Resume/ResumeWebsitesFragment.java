package com.smc.jobs4smcyouth.Fragments.Resume;

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
 * Created by samsiu on 9/5/16.
 */
public class ResumeWebsitesFragment extends Fragment
        implements ResumeWebsitesFragmentRVAdapter.ResumeCardViewClickListener {


    private static final String TAG = ResumeWebsitesFragment.class.getSimpleName();
    private Tracker analyticsTracker;

    public ResumeWebsitesFragment() {

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
        analyticsTracker.setScreenName("Screen~" + "ResumeWebsitesFragment");
        analyticsTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]

        analyticsTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(com.smc.jobs4smcyouth.R.layout.fragment_resume_websites, container, false);

        RecyclerView resumeWebsitesListRecyclerView = (RecyclerView)view.findViewById(com.smc.jobs4smcyouth.R.id.resumeWebsiteListFrag_recyclerView);

        ArrayList<String> urls = new ArrayList<>();
        urls.add("Careeronestop.org");
        //urls.add("Damngood.com");
        urls.add("Eresumes.com");
        urls.add("Resume-help.org");
        urls.add("Resumehelp.com");
        urls.add("Myfuture.com");
        urls.add("Susanireland.com");
        urls.add("Truecareers.com");
        urls.add("Monster.com");
        urls.add("Quintcareers.com/resres.html");


        ArrayList<String> linksImages = new ArrayList<>();
        linksImages.add("http://i.imgur.com/WaMTa7X.png"); // Careeronestop.org
        //linksImages.add("http://i.imgur.com/8AJRm4B.png"); // DamnGood
        linksImages.add("http://i.imgur.com/1FGgwgl.png"); // Eresumes
        linksImages.add("http://i.imgur.com/hHaIwhW.png"); //Resume Help
        linksImages.add("http://i.imgur.com/2vujnwT.png"); // Resume Help2
        linksImages.add("http://i.imgur.com/sLD3ijz.png"); // myfuture
        linksImages.add("http://i.imgur.com/rz13IMg.png"); // susanireland
        linksImages.add("http://i.imgur.com/kEsjSOa.png"); // true careers
        linksImages.add("http://i.imgur.com/hPysrW1.png"); // Monster
        linksImages.add("http://i.imgur.com/3pDJCNO.png"); // QuintCareers


        ResumeWebsitesFragmentRVAdapter resumeWebsitesFragmentRVAdapter = new ResumeWebsitesFragmentRVAdapter(urls, linksImages, this);
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
