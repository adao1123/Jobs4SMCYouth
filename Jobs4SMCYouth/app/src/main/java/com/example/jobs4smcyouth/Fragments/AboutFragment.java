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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements OnMapReadyCallback{
    private TextView phoneDCtextView;
    private TextView phoneAdulttextView;
    private TextView phoneBelmontTextView;
    private TextView phoneRedwoodTextView;
    private String phoneNumber;

    private GoogleMap map;
    private static String latitude;
    private static String longitude;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        initializeViews(v);

        initGoogleMaps();

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

    private void initGoogleMaps(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.about_fragment_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        latitude = "37.7157584";
        longitude = "-122.2140615";
        setGoogleMapCameraPosition(Double.parseDouble(latitude), Double.parseDouble(longitude));
        plotGoogleMapMarker(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }


    private void setGoogleMapCameraPosition(double latitude, double longitude){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)).zoom(10).build();
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    private void plotGoogleMapMarker(double latitude, double longitude){
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude))
                .title("Address")
                .snippet("Snippet");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        map.addMarker(marker);
    }
}
