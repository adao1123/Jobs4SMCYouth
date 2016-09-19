package com.smc.jobs4smcyouth.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smc.jobs4smcyouth.Models.Scholarship;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.smc.jobs4smcyouth.MyApplication;
import com.smc.jobs4smcyouth.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScholarshipFragment extends Fragment {

    private static final String TAG = "Scholarship Fragment";
    private RecyclerView scholarshipRv;
    private Firebase scholarshipFb;
    private TextView contactTv;
    private Intent send;
    private String uriText;

    private Tracker analyticsTracker;

    public ScholarshipFragment() {
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
        Log.i(TAG, "Setting screen name: " + name);
        analyticsTracker.setScreenName("Screen~" + "ScholarshipFragment");
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
        View view = inflater.inflate(com.smc.jobs4smcyouth.R.layout.fragment_scholarship, container, false);
        scholarshipRv = (RecyclerView)view.findViewById(com.smc.jobs4smcyouth.R.id.scholarship_rv);
        clickableContactUs(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFirebase();
        initRV();
    }

    private void initFirebase(){
        Firebase firebaseRef = new Firebase("https://jobs-4-smc-youth.firebaseio.com/");
        scholarshipFb = firebaseRef.child("Scholarships");
        scholarshipFb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChildren()) addListingsToFb();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }


    private void initRV(){
        scholarshipRv.setLayoutManager(new LinearLayoutManager(getContext()));
        scholarshipRv.setAdapter(getFbRvAdapter());
    }

    private FirebaseRecyclerAdapter getFbRvAdapter(){
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Scholarship,ScholarshipViewHolder>(Scholarship.class, R.layout.rv_item_scholarships,ScholarshipViewHolder.class,scholarshipFb){
            @Override
            protected void populateViewHolder(final ScholarshipViewHolder scholarshipViewHolder, final Scholarship scholarship, int i) {
                scholarshipViewHolder.titleTv.setText(scholarship.getTitle());
                scholarshipViewHolder.contactTv.setText(scholarship.getContact());
                scholarshipViewHolder.addressTv.setText(scholarship.getAddress());
                scholarshipViewHolder.emailTv.setText(scholarship.getEmail());
                scholarshipViewHolder.deadlineTv.setText(scholarship.getApplicationDeadline());
                scholarshipViewHolder.numberTv.setText(scholarship.getNumberOfAwards());
                scholarshipViewHolder.maxTv.setText(scholarship.getMax());
                scholarshipViewHolder.descriptionTv.setText(scholarship.getDescription());
                Picasso.with(getContext()).load(scholarship.getIconLink()).resize(200,200).centerCrop().into(scholarshipViewHolder.iconIv);
                Picasso.with(getContext()).load(scholarship.getIconLink()).into(scholarshipViewHolder.iconTopIv);
                scholarshipViewHolder.listingView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (scholarshipViewHolder.descriptionView.getVisibility()!=View.VISIBLE){
                            scholarshipViewHolder.descriptionView.setVisibility(View.VISIBLE);
                            scholarshipViewHolder.contactView.setVisibility(View.VISIBLE);
                            scholarshipViewHolder.emailView.setVisibility(View.VISIBLE);
                            scholarshipViewHolder.numberView.setVisibility(View.VISIBLE);
                            scholarshipViewHolder.iconIv.setVisibility(View.GONE);
                            scholarshipViewHolder.iconTopIv.setVisibility(View.VISIBLE);
                            scholarshipViewHolder.addressView.setVisibility(View.VISIBLE);
                            scholarshipViewHolder.titleTextTv.setVisibility(View.VISIBLE);
                            scholarshipViewHolder.maxTexTv.setVisibility(View.VISIBLE);
                            scholarshipViewHolder.deadlineTextTv.setText("Application Deadline: ");
                            scholarshipViewHolder.titleTv.setTextSize(15);
                            scholarshipViewHolder.titleTv.setTextColor(getContext().getResources().getColor(android.R.color.tab_indicator_text));
                            scholarshipViewHolder.titleTv.setTypeface(Typeface.DEFAULT);
                            scholarshipViewHolder.deadlineTextTv.setTypeface(Typeface.DEFAULT_BOLD);
                            scholarshipViewHolder.deadlineTextTv.setTextColor(getContext().getResources().getColor(R.color.card_view_title));
                            scholarshipViewHolder.switchView.setOrientation(LinearLayout.VERTICAL);
                        }else {
                            scholarshipViewHolder.switchView.setOrientation(LinearLayout.HORIZONTAL);
                            scholarshipViewHolder.titleTextTv.setVisibility(View.GONE);
                            scholarshipViewHolder.titleTv.setTextColor(getContext().getResources().getColor(R.color.card_view_title));
                            scholarshipViewHolder.titleTv.setTextSize(20);
                            scholarshipViewHolder.deadlineTextTv.setText("Due: ");
                            scholarshipViewHolder.deadlineTextTv.setTextColor(getContext().getResources().getColor(android.R.color.tab_indicator_text));
                            scholarshipViewHolder.deadlineTextTv.setTypeface(Typeface.DEFAULT);
                            scholarshipViewHolder.maxTexTv.setVisibility(View.GONE);
//                            scholarshipViewHolder.maxTv.setGravity(View.FOCUS_LEFT);
//                            scholarshipViewHolder.deadlineTextTv.setVisibility(View.GONE);
                            scholarshipViewHolder.iconIv.setVisibility(View.VISIBLE);
                            scholarshipViewHolder.iconTopIv.setVisibility(View.GONE);
                            scholarshipViewHolder.descriptionView.setVisibility(View.GONE);
                            scholarshipViewHolder.addressView.setVisibility(View.GONE);
                            scholarshipViewHolder.contactView.setVisibility(View.GONE);
                            scholarshipViewHolder.emailView.setVisibility(View.GONE);
                            scholarshipViewHolder.numberView.setVisibility(View.GONE);
                            scholarshipViewHolder.titleTv.setTypeface(Typeface.DEFAULT);
                            scholarshipViewHolder.titleTv.setTypeface(Typeface.DEFAULT_BOLD);
                        }
                    }
                });
                scholarshipViewHolder.listingView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                        sendIntent.setType("text/plain");
                        String uriText1 = "mailto:" + Uri.encode(scholarship.getEmail()) +
                                "?subject=" + Uri.encode(scholarship.getTitle()) +
                                "&body=" + Uri.encode("Hi " + scholarship.getContact()+",\n\n\t\t\t\tI saw the "
                        + scholarship.getTitle() + " on the Jobs for Youth app. I am interested in applying and would like to learn more about it.");
                        Uri uri = Uri.parse(uriText1);
                        sendIntent.setData(uri);
                        startActivity(Intent.createChooser(sendIntent, "Send Email"));
                        return true;
                    }
                });
            }
        };
        return adapter;
    }

    protected static class ScholarshipViewHolder extends RecyclerView.ViewHolder{
        TextView titleTv;
        TextView contactTv;
        TextView addressTv;
        TextView emailTv;
        TextView deadlineTv;
        TextView numberTv;
        TextView maxTv;
        TextView descriptionTv;
        TextView titleTextTv;
        TextView deadlineTextTv;
        TextView maxTexTv;
        RoundedImageView iconIv;
        RoundedImageView iconTopIv;
        LinearLayout switchView;
        LinearLayout listingView;
        LinearLayout titleView;
        LinearLayout contactView;
        LinearLayout addressView;
        LinearLayout emailView;
        LinearLayout deadlineView;
        LinearLayout numberView;
        LinearLayout maxView;
        LinearLayout descriptionView;
        public ScholarshipViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView)itemView.findViewById(R.id.scholarship_title);
            contactTv = (TextView)itemView.findViewById(R.id.scholarship_contact);
            addressTv = (TextView)itemView.findViewById(R.id.scholarship_address);
            emailTv = (TextView)itemView.findViewById(R.id.scholarship_email);
            deadlineTv = (TextView)itemView.findViewById(R.id.scholarship_deadline);
            numberTv = (TextView)itemView.findViewById(R.id.scholarship_number);
            maxTv = (TextView)itemView.findViewById(R.id.scholarship_max);
            maxTexTv = (TextView)itemView.findViewById(R.id.scholarship_max_text);
            deadlineTextTv = (TextView)itemView.findViewById(R.id.scholarship_deadline_text);
            titleTextTv = (TextView)itemView.findViewById(R.id.scholarship_title_text);
            descriptionTv = (TextView)itemView.findViewById(R.id.scholarship_description);
            iconIv = (RoundedImageView)itemView.findViewById(R.id.scholarship_icon);
            iconTopIv = (RoundedImageView)itemView.findViewById(R.id.scholarship_icon_top);
            switchView = (LinearLayout)itemView.findViewById(R.id.scholarship_switch_view);
            listingView = (LinearLayout)itemView.findViewById(R.id.scholarship_listing);
            titleView = (LinearLayout)itemView.findViewById(R.id.scholarship_title_view);
            contactView = (LinearLayout)itemView.findViewById(R.id.scholarship_contact_view);
            addressView = (LinearLayout)itemView.findViewById(R.id.scholarship_address_view);
            emailView = (LinearLayout)itemView.findViewById(R.id.scholarship_email_view);
            deadlineView = (LinearLayout)itemView.findViewById(R.id.scholarship_deadline_view);
            numberView = (LinearLayout)itemView.findViewById(R.id.scholarship_number_view);
            maxView = (LinearLayout)itemView.findViewById(R.id.scholarship_max_view);
            descriptionView = (LinearLayout)itemView.findViewById(R.id.scholarship_description_view);
        }
    }
    private void addListingsToFb(){
        for (Scholarship scholarship : makeJobListings()){
            scholarshipFb.push().setValue(scholarship);
        }
    }

    private ArrayList<Scholarship> makeJobListings(){
        ArrayList<Scholarship> scholarships = new ArrayList<>();
        for(int i = 0; i<3;i++) {
            scholarships.add(new Scholarship("WTS Leadership Legacy Scholarship for Graduates", "Scholarship Committee",
                    "3190 SW 66th Ave, Kristia Gladhill, Portland , OR 97225", "gladhillpdx@gmail.com", "October 21, 2016", "1", "$3,500",
                    "The Leadership Legacy Scholarship provides a $3,500 award to a young woman pursuing a career in transportation. Eligible candidates are pursuing graduate degrees in transportation or a related field and demonstrate leadership skills and an active commitment to community service. For this year's award, the focus is on women who demonstrate leadership in bringing ideas, innovation and new approaches to transportation challenges in the US and beyond. It includes candidates who have a specific interest in addressing the impact of transportation on sustainability, land use, environmental impact, security and quality of life issues affecting communities around the globe.",
                    "http://cdn.geekwire.com/wp-content/uploads/2016/06/Woman-in-Tech.jpg"));
            scholarships.add(new Scholarship("NHA Past Presidents' Legacy Scholarship", "Scholarship Management Services",
                    "One Scholarship Way, St. Peter, MN 56082", "perichsen@ScholarshipAmerica.org", "February 15, 2017", "1", "$2,500",
                    "NHA created the Past Presidents’ Legacy Scholarship in 2008 to encourage students to consider becoming part of the U.S. hydropower industry. Our growing industry has professionals in many different fields, including engineering, IT, biology, environmental sciences, forestry, hydrology, animal sciences, management, financial services, communications, and other areas. Many companies also offer high-paying skilled labor and technical positions.",
                    "http://cdn.geekwire.com/wp-content/uploads/2016/06/Woman-in-Tech.jpg"));
            scholarships.add(new Scholarship("Minnesota Masonic Charities Legacy Scholarship", "Kelly Johns",
                    "11501 Masonic Home Drive, Bloomington, MN 55437-3699", "kelly.johns@mnmasonic.org", "February 15, 2017", "10", "$16,000",
                    "Legacy awards are named after benefactors of Minnesota Masonic Charities who have prioritized the pursuit of education with their contributions. Eligible high school seniors must have a 3.6-3.79 GPA to qualify. Candidates must be graduating from a Minnesota high school and must plan to attend a four-year higher education program. Awards are $4000 per year for up to four years or $16,000. Limited to residents of Minnesota. No Masonic affiliation required.",
                    "http://cdn.geekwire.com/wp-content/uploads/2016/06/Woman-in-Tech.jpg"));
            scholarships.add(new Scholarship("Kris Paper Legacy Scholarship For Women In Technology", "Scholarship Committee", "N/A", "N/A", "April 15, 2017", "10", "$1,500",
                    "The Kris Paper Legacy Scholarship for Women in Technology provides an annual scholarship to a graduating female high school senior or returning female college student who intends to further their education at a two-year or four-year college, university, vocational, or technical school who share Kris Paper's love of technology and will be seeking a degree in a technology related field.",
                    "http://cdn.geekwire.com/wp-content/uploads/2016/06/Woman-in-Tech.jpg"));
            scholarships.add(new Scholarship("Army Women's Foundation - Legacy Scholarship", "April Booth", "Scholarship Committe, P.O. Box 5030, Fort Lee, VA 23801",
                    "scholarships@awfdn.org", "January 15, 2017", "30", "$2,500", "The Foundation's Legacy Scholarship program recognizes the importance of education and helping recipients to achieve their educational goals. The Legacy Scholarship Program offers financial support in four areas: Certificate programs, Community College coursework, Undergraduate Degrees, and Graduate Degrees. Scholarships are awarded only for coursework from accredited institutions. Scholarships are based on merit, academic potential, community service, letters of recommendation, and need.",
                    "http://cdn.geekwire.com/wp-content/uploads/2016/06/Woman-in-Tech.jpg"));
        }
        return scholarships;
    }

    private void clickableContactUs(View v){
        contactTv = (TextView) v.findViewById(R.id.scholarship_contact_id);
        contactTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send = new Intent(Intent.ACTION_SENDTO);
                send.setType("text/plain");
                uriText = "mailto:" + Uri.encode("jobs4smcyouth@gmail.com") +
                        "?subject=" + Uri.encode("Al Teglia Jobs for Youth Scholarship Fund") +
                        "&body=" + Uri.encode("");
                Uri uri = Uri.parse(uriText);
                send.setData(uri);
                startActivity(Intent.createChooser(send, "Send Email"));
            }
        });
    }

}
