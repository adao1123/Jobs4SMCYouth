package com.smc.jobs4smcyouth.Fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smc.jobs4smcyouth.Models.AboutLocation;
import com.smc.jobs4smcyouth.MyApplication;
import com.smc.jobs4smcyouth.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/*

271 92nd Street
Daily City, CA 94015
37.6894381,-122.4740778


789 E. Poplar Ave
San Mateo, CA 94401
37.577871,-122.3284768


400 Harbor Blvd Building B
Belmont, CA 94002
37.520316,-122.2693028

2500 Middlefield Road
Redwood City, CA 94063
37.477371,-122.2142568

 */



/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements OnMapReadyCallback{
    private static final String TAG = AboutFragment.class.getSimpleName();

    private TextView phoneDCTextView;
    private TextView phoneAdultTextView;
    private TextView phoneBelmontTextView;
    private TextView phoneRedwoodTextView;

    private GoogleMap map;
    private static String latitude;
    private static String longitude;
    private List<AboutLocation> officeLocations;

    private Tracker analyticsTracker;

    public AboutFragment() {
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
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        initializeViews(v);
        initilizeLocations();
        initGoogleMaps();




        setDialNumberListener(phoneDCTextView, "6503018434");
        setDialNumberListener(phoneAdultTextView, "6503018434");
        setDialNumberListener(phoneBelmontTextView, "6508026534");
        setDialNumberListener(phoneRedwoodTextView, "6508026534");

        return v;
    }

    private void initializeViews(View v){
        phoneDCTextView = (TextView) v.findViewById(R.id.about_phoneDC_tV_id);
        phoneAdultTextView = (TextView) v.findViewById(R.id.about_phoneAdultSchool_tV_id);
        phoneBelmontTextView = (TextView) v.findViewById(R.id.about_phoneBelmont_tV_id);
        phoneRedwoodTextView = (TextView) v.findViewById(R.id.about_phoneRedwood_tV_id);
    }


    private void initilizeLocations(){

        AboutLocation dalyCityLocation = new AboutLocation(
                37.6894381,
                -122.4740778,
                "San Mateo County Human Services Agency", "271 92nd Street \nDaly City, CA 94015",
                "",
                "(650)301-8434", // Phone
                "(650)757-3224"  // Fax
        );

        AboutLocation sanMateoLocation = new AboutLocation(
                37.577871,
                -122.3284768,
                "San Mateo Adult School \n(Satellite Office)",
                "789 E. Poplar Ave \nSan Mateo, CA 94401",
                "No Representative On-Site",
                "(650)301-8434",
                "N/A"
        );

        AboutLocation belmontLocation = new AboutLocation(
                37.520316,
                -122.2693028,
                "San Mateo County Human Services Agency",
                "400 Harbor Blvd Building B \nBelmont, CA 94002",
                "No Representative On-Site",
                "(650)802-6534",
                "(650)508-0782"
        );

        AboutLocation redwoodLocation = new AboutLocation(
                37.477371,
                -122.2142568,
                "San Mateo County Human Services Agency \n(Satellite Office)",
                "2500 Middlefield Road \nRedwood City, CA 94063",
                "No Representative On-Site",
                "(650)802-6534",
                "N/A"
        );

        officeLocations = new ArrayList<>();
        officeLocations.add(dalyCityLocation);
        officeLocations.add(sanMateoLocation);
        officeLocations.add(belmontLocation);
        officeLocations.add(redwoodLocation);

    }

    private void setDialNumberListener(TextView textView, final String phoneNumber){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(phoneNumber);
            }
        });
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

        latitude = "37.577871";
        longitude = "-122.3284768";
        setGoogleMapCameraPosition(Double.parseDouble(latitude), Double.parseDouble(longitude));
        plotSMCLocations();

    }

    private void setGoogleMapCameraPosition(double latitude, double longitude){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)).zoom(9).build();
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    private void plotSMCLocations(){
        for(AboutLocation location : officeLocations){
            String snippet = "Address: " + location.getAddress() +
                    "\nNotes: " + location.getNotes() +
                    "\nPhone: " + location.getPhoneNumber() +
                    "\nFax: " + location.getFaxNumber();

            plotGoogleMapMarker(
                    location.getLatitude(),
                    location.getLongitude(),
                    location.getName(),
                    snippet
            );
        }
    }

    private void plotGoogleMapMarker(double latitude, double longitude, String title, String Snippet){

        setGoogleMarkerInfoTab();

        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude))
                .title(title)
                .snippet(Snippet);

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        map.addMarker(marker);
    }

    private void setGoogleMarkerInfoTab(){
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                Context context = getActivity().getApplicationContext();

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(context);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }
}