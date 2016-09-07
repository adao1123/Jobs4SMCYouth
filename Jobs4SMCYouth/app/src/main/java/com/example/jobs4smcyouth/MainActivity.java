package com.example.jobs4smcyouth;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jobs4smcyouth.Fragments.AboutFragment;
import com.example.jobs4smcyouth.Fragments.Application.ApplicationRulesFragment;
import com.example.jobs4smcyouth.Fragments.Application.ApplicationFragment;
import com.example.jobs4smcyouth.Fragments.InterviewFragment;
import com.example.jobs4smcyouth.Fragments.JobPostFragment;
import com.example.jobs4smcyouth.Fragments.JobSites.JobSiteFragment;
import com.example.jobs4smcyouth.Fragments.RequirementFragment;
import com.example.jobs4smcyouth.Fragments.Resume.ResumeFragment;
import com.example.jobs4smcyouth.Fragments.ScholarshipFragment;
import com.example.jobs4smcyouth.Fragments.SuccessStoryFragment;
import com.example.jobs4smcyouth.Fragments.TipFragment;
import com.example.jobs4smcyouth.Fragments.TransportationFragment;
import com.example.jobs4smcyouth.Utilities.EventBus.ApplicationRulesClickEvent;
import com.example.jobs4smcyouth.Utilities.EventBus.MainBus;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    AboutFragment aboutFragment;
    ApplicationFragment applicationFragment;
    ApplicationRulesFragment applicationRulesFragment;
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
    TextView titleTV;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout_id);
        titleTV = (TextView)findViewById(R.id.title_text_id);
        navigationView = (NavigationView)findViewById(R.id.nvView_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Job Posting");
        setSupportActionBar(toolbar);
        setActionBarDrawer();
        initializeFragments();
        initFragmentManager();
        openScreen();
        setUpDrawerContent(navigationView);
        initializeFragments();

    }

    private void initFragmentManager(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    private void openScreen(){
        fragmentTransaction.replace(R.id.fragment_container_id,jobPostFragment);
        titleTV.setText("Jobs and Interview Listings");
        fragmentTransaction.commit();
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
                titleTV.setText("Jobs and Interview Listings");
                break;
            case R.id.drawer_scholarship_id:
                fragmentTransaction.replace(R.id.fragment_container_id, scholarshipFragment);
                titleTV.setText("Scholarships");
                break;
            case R.id.drawer_transportation_id:
                fragmentTransaction.replace(R.id.fragment_container_id, transportationFragment);
                titleTV.setText("Transportation Vouchers");
                break;
            case R.id.drawer_requirements_id:
                fragmentTransaction.replace(R.id.fragment_container_id, requirementFragment);
                titleTV.setText("Requirements");
                break;
            case R.id.drawer_success_stories_id:
                fragmentTransaction.replace(R.id.fragment_container_id, successStoryFragment);
                titleTV.setText("Success Stories");
                break;
            case R.id.drawer_about_id:
                fragmentTransaction.replace(R.id.fragment_container_id, aboutFragment);
                titleTV.setText("About Jobs4Youth");
                break;
            case R.id.drawer_applications_id:
                fragmentTransaction.replace(R.id.fragment_container_id, applicationFragment);
                titleTV.setText("Applications");
                break;
            case R.id.drawer_interview_id:
                fragmentTransaction.replace(R.id.fragment_container_id, interviewFragment);
                titleTV.setText("Interview Tips");
                break;
            case R.id.drawer_resume_id:
                fragmentTransaction.replace(R.id.fragment_container_id, resumeFragment);
                titleTV.setText("How To Build A Resume");
                break;
            case R.id.drawer_job_site_id:
                fragmentTransaction.replace(R.id.fragment_container_id, jobSiteFragment);
                titleTV.setText("Useful Websites for the Job Search");
                break;
            case R.id.drawer_tips_id:
                fragmentTransaction.replace(R.id.fragment_container_id, tipFragment);
                titleTV.setText("User Tips");
                break;
            default: break;
        }
        fragmentTransaction.commit();
        menuItem.setChecked(true);
        toolbar.setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private void initializeFragments(){
        aboutFragment = new AboutFragment();
        applicationFragment = new ApplicationFragment();
        applicationRulesFragment = new ApplicationRulesFragment();
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


    private void setActionBarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout_id);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private Subscriber<? super Object> mainBusSubscriber = new Subscriber<Object>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {
            if (o instanceof ApplicationRulesClickEvent){
                initFragmentManager();
                fragmentTransaction.addToBackStack("Application");
                fragmentTransaction.replace(R.id.fragment_container_id, applicationRulesFragment);
                fragmentTransaction.commit();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        MainBus.getInstance().getBusObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainBusSubscriber);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainBusSubscriber.unsubscribe();
    }
}


