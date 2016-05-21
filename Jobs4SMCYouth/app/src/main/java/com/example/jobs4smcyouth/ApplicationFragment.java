package com.example.jobs4smcyouth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicationFragment extends Fragment {


    public ApplicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application, container, false);

        ImageView masterForm1ImageView = (ImageView)view.findViewById(R.id.application_masterForm1_imageView);
        ImageView masterForm2ImageView = (ImageView)view.findViewById(R.id.application_masterForm2_imageView);
        ImageView volunteerForm1ImageView = (ImageView)view.findViewById(R.id.application_sampleVolunteerForm1_imageView);
        ImageView volunteerForm2ImageView = (ImageView)view.findViewById(R.id.application_sampleVolunteerForm2_imageView);
        ImageView noExperienceForm1ImageView = (ImageView)view.findViewById(R.id.application_noExperienceForm1_imageView);
        ImageView noExperienceForm2ImageView = (ImageView)view.findViewById(R.id.application_noExperienceForm2_imageView);
        ImageView jobExperienceForm1ImageView = (ImageView)view.findViewById(R.id.application_jobExperienceForm1_imageView);
        ImageView jobExperienceForm2ImageView = (ImageView)view.findViewById(R.id.application_jobExperienceForm2_imageView);

        List<String> links = new ArrayList<String>();
        links.add("http://i.imgur.com/OlLu3bc.png"); // MasterApplication Page 1
        links.add("http://i.imgur.com/SSd9mo2.png"); // MasterApplication Page 2
        links.add("http://i.imgur.com/oNepSHq.png"); // SampleVolunteerExperience1
        links.add("http://i.imgur.com/g4IjG5j.png"); // SampleVolunteerExperience2
        links.add("http://i.imgur.com/0cJNawb.png"); // SampleNoExperience1
        links.add("http://i.imgur.com/QydztcF.png"); // SampleNoExperience2
        links.add("http://i.imgur.com/zoqznjx.png"); // SampleJobExperience1
        links.add("http://i.imgur.com/oKi8Mk4.png"); // SampleJobExperience2

        List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(masterForm2ImageView);
        imageViews.add(volunteerForm1ImageView);
        imageViews.add(volunteerForm2ImageView);
        imageViews.add(noExperienceForm1ImageView);
        imageViews.add(noExperienceForm2ImageView);
        imageViews.add(jobExperienceForm1ImageView);
        imageViews.add(jobExperienceForm2ImageView);

        for(int i = 0; i < imageViews.size(); i++){
            Picasso.with(getContext())
                    .load(links.get(i))
                    .resize(1000, 1700)
                    .into(imageViews.get(i));
        }

        return view;
    }

}
