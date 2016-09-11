package com.example.jobs4smcyouth.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jobs4smcyouth.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransportationFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TransportWebViewFragment transportWebViewFragment;
    private TextView contactTv;
    private Intent send;
    private String uriText;


    public TransportationFragment() {
        // Required empty public constructor
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
        fragmentTransaction.add(R.id.transportation_wVContainer_id, transportWebViewFragment);
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
