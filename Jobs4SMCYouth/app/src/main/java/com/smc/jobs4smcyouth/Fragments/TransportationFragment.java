package com.smc.jobs4smcyouth.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
public class TransportationFragment extends Fragment {

    private static final String TAG = TransportationFragment.class.getSimpleName();

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TransportWebViewFragment transportWebViewFragment;
    private TextView contactTv;
    private Intent send;
    private String uriText;

    private Tracker analyticsTracker;

    public TransportationFragment() {
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
        analyticsTracker.setScreenName("Screen~" + "TransportationFragment");
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
        View v = inflater.inflate(R.layout.fragment_transportation, container, false);
        clickableContactUs(v);
        transportWebViewFragment = new TransportWebViewFragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(com.smc.jobs4smcyouth.R.id.transportation_wVContainer_id, transportWebViewFragment);
        fragmentTransaction.commit();
        return v;
    }

    private void clickableContactUs(View v){
        contactTv = (TextView) v.findViewById(R.id.transportation_contact_id);
        contactTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send = new Intent(Intent.ACTION_SENDTO);
                send.setType("text/plain");
                uriText = "mailto:" + Uri.encode("jobs4smcyouth@gmail.com") +
                        "?subject=" + Uri.encode("Transportation Assistance") +
                        "&body=" + Uri.encode("");
                Uri uri = Uri.parse(uriText);
                send.setData(uri);
                startActivity(Intent.createChooser(send, "Send Email"));
            }
        });
    }

}
