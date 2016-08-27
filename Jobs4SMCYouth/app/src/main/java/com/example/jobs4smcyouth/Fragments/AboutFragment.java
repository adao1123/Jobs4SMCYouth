package com.example.jobs4smcyouth.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jobs4smcyouth.R;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    private TextView phoneDCtextView;
    private TextView phoneAdulttextView;
    private TextView phoneBelmontTextView;
    private TextView phoneRedwoodTextView;
    private String phoneNumber;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        initializeViews(v);

        phoneDCtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = "6503018434";
                dialPhoneNumber(phoneNumber);
            }
        });

        phoneAdulttextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = "6503018434";
                dialPhoneNumber(phoneNumber);
            }
        });

        phoneBelmontTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = "6508026534";
                dialPhoneNumber(phoneNumber);
            }
        });

        phoneRedwoodTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = "6508026534";
                dialPhoneNumber(phoneNumber);
            }
        });

        return v;
    }

    private void initializeViews(View v){
        phoneDCtextView = (TextView) v.findViewById(R.id.about_phoneDC_tV_id);
        phoneAdulttextView = (TextView) v.findViewById(R.id.about_phoneAdultSchool_tV_id);
        phoneBelmontTextView = (TextView) v.findViewById(R.id.about_phoneBelmont_tV_id);
        phoneRedwoodTextView = (TextView) v.findViewById(R.id.about_phoneRedwood_tV_id);
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
