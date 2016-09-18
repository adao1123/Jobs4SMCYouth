package com.smc.jobs4smcyouth.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smc.jobs4smcyouth.MyApplication;
import com.smc.jobs4smcyouth.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InterviewFragment extends Fragment {

    private static final String TAG = InterviewFragment.class.getSimpleName();
    private CardView beforeCard;
    private CardView duringCard;
    private CardView afterCard;
    private TextView beforeDetail;
    private TextView duringDetail;
    private TextView afterDetail;

    private Tracker analyticsTracker;

    public InterviewFragment() {
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
        analyticsTracker.setScreenName("Screen~" + "AboutFragment");
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
        View v = inflater.inflate(R.layout.fragment_interview, container, false);
        initiateViews(v);
        expandDetails();
        return v;
    }

    private void initiateViews(View v){
        beforeCard = (CardView) v.findViewById(R.id.interview_before_card_id);
        duringCard = (CardView) v.findViewById(R.id.interview_during_card_id);
        afterCard = (CardView) v.findViewById(R.id.interview_after_card_id);
        beforeDetail = (TextView) v.findViewById(R.id.interview_before_detail_id);
        duringDetail = (TextView) v.findViewById(R.id.interview_during_detail_id);
        afterDetail = (TextView) v.findViewById(R.id.interview_after_detail_id);
        beforeDetail.setVisibility(View.GONE);
        duringDetail.setVisibility(View.GONE);
        afterDetail.setVisibility(View.GONE);
    }

    private void expandDetails(){
        beforeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(beforeDetail.getVisibility() == View.GONE){
                    beforeDetail.setVisibility(View.VISIBLE);
                } else{
                    beforeDetail.setVisibility(View.GONE);
                }
            }
        });

        duringCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(duringDetail.getVisibility() == View.GONE){
                    duringDetail.setVisibility(View.VISIBLE);
                } else{
                    duringDetail.setVisibility(View.GONE);
                }
            }
        });

        afterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(afterDetail.getVisibility() == View.GONE){
                    afterDetail.setVisibility(View.VISIBLE);
                } else{
                    afterDetail.setVisibility(View.GONE);
                }
            }
        });
    }

}
