package com.example.jobs4smcyouth;

import android.app.Application;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    AboutFragment aboutFragment;
    ApplicationFragment applicationFragment;
    InterviewFragment interviewFragment;
    JobPostFragment jobPostFragment;
    JobSiteFragment jobSiteFragment;
    RequirementFragment requirementFragment;
    ResumeFragment resumeFragment;
    ScholarshipFragment scholarshipFragment;
    SuccessStoryFragment successStoryFragment;
    TipFragment tipFragment;
    TransportationFragment transportationFragment;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout_id);
        navigationView = (NavigationView)findViewById(R.id.nvView_id);
        setUpDrawerContent(navigationView);
        initializeFragments();

    }

    private void initFragmentManager(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    private void setUpDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem){
        initFragmentManager();
        fragmentTransaction.addToBackStack(null);
        switch (menuItem.getItemId()){
            case R.id.drawer_job_post_id:
                fragmentTransaction.replace(R.id.fragment_container_id, jobPostFragment);
                break;
            case R.id.drawer_scholarship_id:
                fragmentTransaction.replace(R.id.fragment_container_id, scholarshipFragment);
                break;
            case R.id.drawer_transportation_id:
                fragmentTransaction.replace(R.id.fragment_container_id, transportationFragment);
                break;
            case R.id.drawer_requirements_id:
                fragmentTransaction.replace(R.id.fragment_container_id, requirementFragment);
                break;
            case R.id.drawer_success_stories_id:
                fragmentTransaction.replace(R.id.fragment_container_id, successStoryFragment);
                break;
            case R.id.drawer_about_id:
                fragmentTransaction.replace(R.id.fragment_container_id, aboutFragment);
                break;
            case R.id.drawer_applications_id:
                fragmentTransaction.replace(R.id.fragment_container_id, applicationFragment);
                break;
            case R.id.drawer_interview_id:
                fragmentTransaction.replace(R.id.fragment_container_id, interviewFragment);
                break;
            case R.id.drawer_resume_id:
                fragmentTransaction.replace(R.id.fragment_container_id, resumeFragment);
                break;
            case R.id.drawer_job_site_id:
                fragmentTransaction.replace(R.id.fragment_container_id, jobSiteFragment);
                break;
            case R.id.drawer_tips_id:
                fragmentTransaction.replace(R.id.fragment_container_id, tipFragment);
                break;
            default: break;
        }
        fragmentTransaction.commit();
        menuItem.setChecked(true);
        //setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private void initializeFragments(){
        aboutFragment = new AboutFragment();
        applicationFragment = new ApplicationFragment();
        interviewFragment = new InterviewFragment();
        jobPostFragment = new JobPostFragment();
        jobSiteFragment = new JobSiteFragment();
        requirementFragment = new RequirementFragment();
        resumeFragment = new ResumeFragment();
        scholarshipFragment = new ScholarshipFragment();
        successStoryFragment = new SuccessStoryFragment();
        tipFragment = new TipFragment();
        transportationFragment = new TransportationFragment();
    }

}
