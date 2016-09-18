package com.smc.jobs4smcyouth.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smc.jobs4smcyouth.MyApplication;
import com.smc.jobs4smcyouth.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequirementFragment extends Fragment {
    private static final String TAG = RequirementFragment.class.getSimpleName();

    Button laborLawsButton;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    LaborLawsWebviewFragment laborLawsWebviewFragment;

    private Tracker analyticsTracker;

    private CardView requirement1CardView;
    private LinearLayout requirementDetailLinearLayout;

    private Animation slideUp;
    private Animation slideDown;


    public RequirementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        MyApplication application = (MyApplication) getActivity().getApplication();
        analyticsTracker = application.getDefaultTracker();
        sendScreenImageName();
    }

    private void sendScreenImageName(){
        String name = TAG;
        // [START screen_view_hit]
        Log.i(TAG, "Setting Screen Name: " + name);
        analyticsTracker.setScreenName("Screen~ " + "RequirementsFragment");
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
        View view = inflater.inflate(R.layout.fragment_requirement, container, false);

        initializeViews(view);
        loadAnimation();
        expandDetails();

        laborLawsButton = (Button) view.findViewById(R.id.requirements_lawButton_id);
        laborLawsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laborLawsWebviewFragment = new LaborLawsWebviewFragment();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_id, laborLawsWebviewFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    private void initializeViews(View view){
        requirement1CardView = (CardView)view.findViewById(R.id.requirements_workPermitCard_id);
        requirementDetailLinearLayout = (LinearLayout)view.findViewById(R.id.requirement_permit_detail_linearLayout);
        requirementDetailLinearLayout.setVisibility(View.GONE);
    }

    private void loadAnimation(){
        slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
        slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
    }

    private void expandDetails(){
        requirement1CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(requirementDetailLinearLayout.getVisibility() == View.GONE){
                    requirementDetailLinearLayout.setVisibility(View.VISIBLE);
                    requirementDetailLinearLayout.startAnimation(slideDown);
                }else{
                    requirementDetailLinearLayout.setVisibility(View.GONE);
                    requirementDetailLinearLayout.setAnimation(slideUp);
                }
            }
        });
    }

}
