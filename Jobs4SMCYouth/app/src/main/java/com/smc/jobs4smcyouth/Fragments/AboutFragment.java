package com.smc.jobs4smcyouth.Fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private TextView smcEmailTextView;
    private CardView aboutCardView;
    private TextView aboutDetail;
    //private LinearLayout aboutDetail;
    private CardView servicesCardView;
    //private LinearLayout servicesDetail;
    private TextView servicesDetail;

    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private static String latitude;
    private static String longitude;
    private List<AboutLocation> officeLocations;

    private Tracker analyticsTracker;
    private View view;

    private Animation slideUp;
    private Animation slideDown;
    private Animation fadeIn;
    private Animation fadeOut;

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
        super.onCreateView(inflater, container, savedInstanceState);

        if(view == null){
            view = inflater.inflate(R.layout.fragment_about, container, false);
        }

        initGoogleMaps();
        initializeViews(view);
        initilizeLocations();

        loadAnimation();
        expandDetails();

        setDialNumberListener(phoneDCTextView, "6503018434");
        setDialNumberListener(phoneAdultTextView, "6503018434");
        setDialNumberListener(phoneBelmontTextView, "6508026534");
        setDialNumberListener(phoneRedwoodTextView, "6508026534");

        setSendEmailListener(smcEmailTextView , "jobsforyouth@smcgov.org");

        return view;
    }

    private void initializeViews(View v){
        phoneDCTextView = (TextView) v.findViewById(R.id.about_phoneDC_tV_id);
        phoneAdultTextView = (TextView) v.findViewById(R.id.about_phoneAdultSchool_tV_id);
        phoneBelmontTextView = (TextView) v.findViewById(R.id.about_phoneBelmont_tV_id);
        phoneRedwoodTextView = (TextView) v.findViewById(R.id.about_phoneRedwood_tV_id);
        smcEmailTextView = (TextView) v.findViewById(R.id.about_email_tv_id);
        aboutCardView = (CardView)v.findViewById(R.id.about_aboutCard_id);
        //aboutDetail = (LinearLayout)v.findViewById(R.id.about_aboutInfo_LinearLayout);
        aboutDetail = (TextView)v.findViewById(R.id.about_about_tv_id);
        servicesCardView = (CardView)v.findViewById(R.id.about_servicesCard_id);
        //servicesDetail = (LinearLayout)v.findViewById(R.id.about_servicesOffered_LinearLayout);
        servicesDetail = (TextView)v.findViewById(R.id.about_servicesOffered_tv_id);
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

    private void setSendEmailListener(TextView textView, final String email){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "email clicked");
                sendEmail(email);
            }
        });
    }

    public void sendEmail(String email){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        emailIntent.setType("message/rfc822");
        emailIntent.setData(Uri.parse("mailto:"+email));

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Jobs For Youth Inquiry");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi Jobs for Youth, \n");

        if(emailIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivity(emailIntent);
        }
    }

    private void loadAnimation(){
        slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
        slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
    }

    private void expandDetails(){
        aboutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aboutDetail.getVisibility() == View.GONE){
                    aboutDetail.setVisibility(View.VISIBLE);
                    aboutDetail.setAnimation(fadeIn);
                }else{
                    aboutDetail.setVisibility(View.GONE);
                    aboutDetail.setAnimation(fadeOut);
                }
            }
        });

        servicesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(servicesDetail.getVisibility() == View.GONE){
                    servicesDetail.setVisibility(View.VISIBLE);
                    servicesDetail.setAnimation(fadeIn);
                }else{
                    servicesDetail.setVisibility(View.GONE);
                    servicesDetail.setAnimation(fadeOut);
                }
            }
        });

    }



    private void initGoogleMaps(){
        if(map == null){
            mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.about_fragment_map);
            mapFragment.getMapAsync(this);
        }

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
