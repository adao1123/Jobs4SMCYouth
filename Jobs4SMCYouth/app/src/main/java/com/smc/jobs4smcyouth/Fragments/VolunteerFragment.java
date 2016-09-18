package com.smc.jobs4smcyouth.Fragments;


import android.graphics.Typeface;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smc.jobs4smcyouth.MyApplication;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smc.jobs4smcyouth.Models.VolunteerOpportunity;
import com.smc.jobs4smcyouth.R;
import com.smc.jobs4smcyouth.Utilities.WebViewFragment.WebViewFragment;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolunteerFragment extends Fragment {
    private static final String TAG = "VOLUNTEER POST FRAGMENT";
    private Firebase firebaseListings;
    private RecyclerView volunteerListingRV;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    private Tracker analyticsTracker;

    public VolunteerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        MyApplication application = (MyApplication)getActivity().getApplication();
        analyticsTracker = application.getDefaultTracker();
        sendScreenImageName();
    }

    private void sendScreenImageName(){
        String name = TAG;
        // [START screen_view_hit]
        Log.i(TAG, "Setting Screen Name: " + TAG);
        analyticsTracker.setScreenName("Screen~" + "VolunteerFragment");
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
        View view = inflater.inflate(R.layout.fragment_volunteer, container, false);
        volunteerListingRV = (RecyclerView) view.findViewById(R.id.volunteer_rv_id);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleFirebase();
        displayJobListings();
    }


    private void handleFirebase(){
        Firebase firebaseRef = new Firebase("https://jobs-4-smc-youth.firebaseio.com/");
        firebaseListings = firebaseRef.child("Volunteering Opportunities");
        firebaseListings.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChildren()) addListingsToFb();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void displayJobListings(){
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<VolunteerOpportunity,VolunteerViewHolder>(VolunteerOpportunity.class,R.layout.rv_item_volunteer,VolunteerViewHolder.class,firebaseListings){
            @Override
            protected void populateViewHolder(final VolunteerViewHolder volunteerViewHolder, final VolunteerOpportunity volunteerOpportunity, int i) {
                volunteerViewHolder.volunteerTitleTV.setText(volunteerOpportunity.getTitle());
                volunteerViewHolder.volunteerAddressTV.setText(volunteerOpportunity.getAddress());
                volunteerViewHolder.volunteerAboutTV.setText(volunteerOpportunity.getAbout());
                volunteerViewHolder.volunteerDateTV.setText(volunteerOpportunity.getDate());
                volunteerViewHolder.volunteerOrganizerTV.setText(volunteerOpportunity.getOrganizer());
                volunteerViewHolder.volunteerWebsiteTV.setText(volunteerOpportunity.getWebsite());
                volunteerViewHolder.volunteerListingView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url",volunteerOpportunity.getWebsite().substring(11));

                        WebViewFragment webViewFragment = new WebViewFragment();
                        webViewFragment.setArguments(bundle);

                        FragmentManager fragmentManager;
                        fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.addToBackStack("Jobs");
                        fragmentTransaction.replace(com.smc.jobs4smcyouth.R.id.fragment_container_id, webViewFragment);
                        fragmentTransaction.commit();

                        return true;
                    }
                });
                volunteerViewHolder.volunteerListingView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "onClick: ");
                        if (volunteerViewHolder.aboutView.getVisibility()!=View.VISIBLE){
                            volunteerViewHolder.addressView.setVisibility(View.VISIBLE);
                            volunteerViewHolder.aboutView.setVisibility(View.VISIBLE);
                            volunteerViewHolder.dateView.setVisibility(View.VISIBLE);
                            volunteerViewHolder.organizerView.setVisibility(View.VISIBLE);
                            volunteerViewHolder.websiteView.setVisibility(View.VISIBLE);
                            volunteerViewHolder.volunteerImage.setVisibility(View.GONE);
                            volunteerViewHolder.volunteerImageTop.setVisibility(View.VISIBLE);
                            volunteerViewHolder.volunteerTitleWord.setVisibility(View.VISIBLE);
                            volunteerViewHolder.volunteerTitleTV.setTextSize(15);
                            volunteerViewHolder.volunteerTitleTV.setTypeface(Typeface.DEFAULT);
                            volunteerViewHolder.volunteerTitleTV.setTextColor(getContext().getResources().getColor(android.R.color.tab_indicator_text));


                        }else{
                            volunteerViewHolder.volunteerTitleTV.setTextColor(getContext().getResources().getColor(R.color.card_view_title));
                            volunteerViewHolder.volunteerTitleTV.setTypeface(Typeface.DEFAULT_BOLD);
                            volunteerViewHolder.volunteerTitleTV.setTextSize(20);
                            volunteerViewHolder.volunteerTitleWord.setVisibility(View.GONE);
                            volunteerViewHolder.addressView.setVisibility(View.GONE);
                            volunteerViewHolder.aboutView.setVisibility(View.GONE);
                            volunteerViewHolder.dateView.setVisibility(View.GONE);
                            volunteerViewHolder.organizerView.setVisibility(View.GONE);
                            volunteerViewHolder.websiteView.setVisibility(View.GONE);
                            volunteerViewHolder.volunteerImageTop.setVisibility(View.GONE);
                            volunteerViewHolder.volunteerImage.setVisibility(View.VISIBLE);

                        }
                    }
                });
            }
        };
        volunteerListingRV.setAdapter(adapter);
        volunteerListingRV.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    protected static class VolunteerViewHolder extends RecyclerView.ViewHolder{
        public TextView volunteerTitleTV;
        public TextView volunteerAddressTV; //city
        public TextView volunteerAboutTV; //duty
        public TextView volunteerDateTV; //payrate
        public TextView volunteerOrganizerTV; //age
        public TextView volunteerWebsiteTV;
        public TextView volunteerTitleWord;
        public RoundedImageView volunteerImage;
        public RoundedImageView volunteerImageTop;
        public LinearLayout volunteerListingView;
        public LinearLayout titleView;
        public LinearLayout addressView;
        public LinearLayout aboutView;
        public LinearLayout dateView;
        public LinearLayout organizerView;
        public LinearLayout websiteView;
        public VolunteerViewHolder(View itemView) {
            super(itemView);
            volunteerImage = (RoundedImageView)itemView.findViewById(R.id.rv_item_volunteer_image);
            volunteerImageTop = (RoundedImageView)itemView.findViewById(R.id.rv_item_volunteer_image_top);
            volunteerTitleTV = (TextView)itemView.findViewById(R.id.rv_item_volunteer_title);
            volunteerAddressTV = (TextView)itemView.findViewById(R.id.rv_item_volunteer_address);
            volunteerAboutTV = (TextView)itemView.findViewById(R.id.rv_item_volunteer_about);
            volunteerDateTV = (TextView)itemView.findViewById(R.id.rv_item_volunteer_date);
            volunteerOrganizerTV = (TextView)itemView.findViewById(R.id.rv_item_volunteer_organizer);
            volunteerWebsiteTV = (TextView)itemView.findViewById(R.id.rv_item_volunteer_website);
            volunteerTitleWord = (TextView)itemView.findViewById(R.id.volunteer_title_text);
            titleView = (LinearLayout)itemView.findViewById(R.id.volunteer_title_view);
            addressView = (LinearLayout)itemView.findViewById(R.id.volunteer_address_view);
            dateView = (LinearLayout)itemView.findViewById(R.id.volunteer_date_view);
            organizerView = (LinearLayout)itemView.findViewById(R.id.volunteer_organizer_view);
            aboutView = (LinearLayout)itemView.findViewById(R.id.volunteer_about_view);
            websiteView = (LinearLayout)itemView.findViewById(R.id.volunteer_website_view);
            volunteerListingView = (LinearLayout)itemView.findViewById(R.id.volunteer_listing);
        }
    }

    private void addListingsToFb(){
        for (VolunteerOpportunity volunteerOpportunity : makeVolunteerListings()){
            firebaseListings.push().setValue(volunteerOpportunity);
        }
    }

    private ArrayList<VolunteerOpportunity> makeVolunteerListings(){
        ArrayList<VolunteerOpportunity> volunteerOpportunities = new ArrayList<>();
        volunteerOpportunities.add(new VolunteerOpportunity("Help a child be successful in school. Volunteer in our Homework Clubs and Literacy Classes!",
                "Daly City Partnership","111 Lake Merced Blvd. Daly City, CA 94015","Flexible",
                "Interested in obtaining valuable and rewarding experience working with children? Volunteer with us in our after school programs! You can help a child succeed in school and in life today. Mentor children in grades K-8 while helping them with their homework, leading them in enriching activities and reading stories to them. Scheduling and location are flexible and your time is appreciated. As schools face more and more budget cuts, the quality of the education our children receive is lowered. Your role as a volunteer could change a child's life.",
                "http://www.volunteermatch.org/search/opp1193571.jsp"));
        volunteerOpportunities.add(new VolunteerOpportunity("No More Trash! Mussel Rock Beach Cleanup",
                "Pacifica Beach Coalition","120 Westline Dr. Daly City, CA 94015","Sat Oct 22, 2016, 09:00 AM - 11:00 AM",
                "Let's get rid of the trash but keep the ocean view! The Pacifica Beach Coalition supports a monthly coastal clean up from 9am to 11am, meeting at Mussel Rock car park situated at 120 Westline Drive, Daly City, California. The clean up site covers part of the northern most tip of Pacifica adjoining Daly City that receives strong winds that distributes plastic trash from the neighboring transfer station. It is a beach, bluff, and street clean up with vistas of ocean from every point. Please join us and learn about this beautiful area that sits on the San Andreas Fault, has mammoth remains embedded in the cliffs, and has also been an Ohlone Indian camp site and midden because of the abundance of mussels and other ocean foods. Supply provide but bring your own bucket and gloves if you can. We hope to see you there!",
                "http://www.volunteermatch.org/search/opp1827354.jsp"));
        volunteerOpportunities.add(new VolunteerOpportunity("California Coastal Cleanup Day - Come Join Us!",
                "Pacifica Beach Coalition","Pacifica State Beach, Pacifica, CA 94044","Sat Sep 17, 2016, 09:00 AM - 11:00 AM",
                "Coastal Cleanup Day (CCD) is an annual beach and waterway clean up held on the third Saturday of September. It is California's largest volunteer event and brings community awareness to cleaning up and protecting our marine environment.\n" +
                        "\n" +
                        "So come join us at the location of your choice:\n" +
                        "\n" +
                        "Mussel Rock - Daly City \n" +
                        "Pillar Point - Half Moon Bay\n" +
                        "Linda Mar - Pacifica\n" +
                        "Rockaway - Pacifica\n" +
                        "Sharp Park - Pacifica\n" +
                        "Tunitas Creek - Tunitas\n" +
                        "\n" +
                        "We will provide supplies but bring your own bucket and gloves if you have.\n" +
                        "\n" +
                        "Don't hesitate to contact us for more details","http://www.volunteermatch.org/search/opp1759196.jsp"));
        volunteerOpportunities.add(new VolunteerOpportunity("Google Code Corps Guru","Boys & Girls Clubs of North San Mateo County",
                "201 W. Orange Ave. South San Francisco, CA 94080", "Mon Sep 05, 2016 - Mon Dec 05, 2016",
                "Are you passionate about empowering underprivileged youth? Do you want to fight poverty using education? The Google Code Corps might be for you!\n" +
                        "\n" +
                        "The Google Code Corps is a partnership between Google, AmeriCorps, and the Boys & Girls Club aimed at empowering youth through access to a quality computer science education. We are looking for volunteers to help us teach underprivileged children how to code via Google’s CS First curriculum. This opportunity is located in South San Francisco, accessible by BART. We will be teaching children aged 9-14 the basics of computer science (Scratch, robotics, etc.) to enable them to be successful and break the cycle of poverty.\n" +
                        "\n" +
                        "Volunteers can expect a time commitment of 3 hours a week for 3 months. Computer science experience and/or experience with youth are preferred but not required. You will be trained and coached by Google Code Corps staff. Our summer cohort has been extremely successful, and we are looking for to fill our fall cohort, which starts in September. Please send resumes to pchan@theclubs.org or send a private message if you have questions. Thanks!",
                "http://www.volunteermatch.org/search/opp2427371.jsp"));
        volunteerOpportunities.add(new VolunteerOpportunity("Come Help Deaf Children and Have Fun!","Weingarten Children's Center",
                "5101 Great America Parkway, Santa Clara, CA 95054","Sat Oct 29, 2016, 08:00 AM - 11:59 PM",
                "We are hosting our annual Benefit Gala this year on Saturday, October 29, 2016 at the Santa Clara Hyatt Regency. We are in need of volunteers for the event. Volunteers are essential to the success of this crucial fundraising event! The details are as follow:\n" +
                        "\n" +
                        "When: Saturday, October 29, 2016\n" +
                        "We need volunteers from 8:00 AM to Midnight. Feel free to come for an hour or all day! \n" +
                        "\n" +
                        "Where: Santa Clara Hyatt Regency\n" +
                        "5101 Great America Parkway \n" +
                        "Santa Clara, CA 95054\n" +
                        "\n" +
                        "Volunteering responsibilities and approximate timeframes are:\n" +
                        "\n" +
                        "8:00 AM to 12:00 PM- setting up silent auction and live auction, decorating the ballroom\n" +
                        "\n" +
                        "12:00 PM to 4:00 PM - setting up silent auction and live auction, decorating the ballroom, setting up dinner area\n" +
                        "\n" +
                        "4:00 PM to 8:00 PM - assisting with check in, silent auction, breaking down of silent auction, organizing auction purchases, assisting with check out\n" +
                        "\n" +
                        "8:00 PM to Midnight- assisting with dance area, assisting with check out, breaking down and cleaning up after the event \n" +
                        "\n" +
                        "Please wear a black collared/buttoned down shirt and a pair of khakis.\n" +
                        "\n" +
                        "Here are directions to the event:\n" +
                        "\n" +
                        "From Points North:\n" +
                        "Take I-880 South and exit to Highway 237 West toward Mountain View. Turn left onto Great America Parkway, and then turn left into the hotel entrance. Hyatt Regency Santa Clara is located at the corner of Tasman and Great America Parkway.\n" +
                        "\n" +
                        "From Points South:\n" +
                        "Take I-880 North to Highway 101 North and exit at Great America Parkway. Turn right onto Great America Parkway. Hyatt Regency Santa Clara is located on the corner of Tasman and Great America Parkway.\n" +
                        "\n" +
                        "From Points East:\n" +
                        "Take I-5 South toward San Francisco / Los Angeles. Merge onto I-205 West toward I-580 / San Francisco; I-205 becomes I-580 West. Take the I-680 North exit toward Sacramento / San Jose, then merge onto I-680 South via the exit on the left toward San Jose. Take the Mission Boulevard West exit toward I-880 / Warm Springs District / UC Extension. Merge onto Mission Boulevard / CA-262 West, then merge onto I-880 South toward San Jose. Merge onto CA-237 West / Aliviso-Milipitas Road toward Mountain View. Take the Great America Parkway exit toward Lafayette Street, turn left onto Great America Parkway.\n" +
                        "\n" +
                        "The success of this event is dependent on our volunteers, and we couldn't have done it without you this last couple of years. This is our most important fundraiser event of the year as it encompasses a large portion of our annual budget. Thank you for being so generous and being a part of our event!\n" +
                        "\n" +
                        "Please do not hesitate to contact me with any questions. I can be reached at jgreenman@weingartencc.org.\n" +
                        "\n" +
                        "Thank you!",
                "http://www.volunteermatch.org/search/opp1808730.jsp"));
        volunteerOpportunities.add(new VolunteerOpportunity("Volunteer at the South San Francisco Farmers' Market on Saturdays!",
                "Pacific Coast Farmers' Market Association","35 West Orange Ave. South San Francisco, CA 94080","Flexible",
                "Enjoy having access to fresh, California-grown fruits and veggies? Love the community at your local farmers’ market? Want to learn more about local and sustainable agriculture, and help others to learn more about it too? Get involved as a volunteer at the Inner Sunset Farmers' Market!\n" +
                        "\n" +
                        "The South SF Farmers' Market operates every Saturday from 10 am-2 pm at 35 West Orange Ave in South San Francisco.\n" +
                        "\n" +
                        "In order to better serve the communities we work in, PCFMA is seeking volunteers to assist with activities such as:\n" +
                        "\n" +
                        "1) Providing a positive and friendly presence at PCFMA’s info booth at the market\n" +
                        "\n" +
                        "2) Providing shoppers with information about upcoming events and what’s new at the market\n" +
                        "\n" +
                        "3) Processing CalFresh (food stamps), so that recipients are able to purchase fresh, healthy food\n" +
                        "\n" +
                        "4) Special events such as fresh produce samplings, kids’ activities and interactive learning games\n" +
                        "\n" +
                        "5) Assisting the market manager with other tasks as necessary",
                "http://www.volunteermatch.org/search/opp2429044.jsp"));
        return volunteerOpportunities;
    }

}
