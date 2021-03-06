package com.smc.jobs4smcyouth.Models;

/**
 * Created by adao1 on 5/21/2016.
 */
public class JobListing {
    private String jobTitle;
    private String jobCity;
    private String jobDuty;
    private String jobPayrate;
    private String jobAge;
    private String jobImageLink;

    public JobListing() {
    }

    public JobListing(String jobTitle, String jobCity, String jobDuty, String jobPayrate, String jobAge, String jobImageLink) {
        this.jobTitle = jobTitle;
        this.jobCity = jobCity;
        this.jobDuty = jobDuty;
        this.jobPayrate = jobPayrate;
        this.jobAge = jobAge;
        this.jobImageLink = jobImageLink;

    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobCity() {
        return jobCity;
    }

    public void setJobCity(String jobCity) {
        this.jobCity = jobCity;
    }

    public String getJobDuty() {
        return jobDuty;
    }

    public void setJobDuty(String jobDuty) {
        this.jobDuty = jobDuty;
    }

    public String getJobPayrate() {
        return jobPayrate;
    }

    public void setJobPayrate(String jobPayrate) {
        this.jobPayrate = jobPayrate;
    }

    public String getJobAge() {
        return jobAge;
    }

    public void setJobAge(String jobAge) {
        this.jobAge = jobAge;
    }

    public String getJobImageLink() {
        return jobImageLink;
    }

    public void setJobImageLink(String jobImageLink) {
        this.jobImageLink = jobImageLink;
    }

}
