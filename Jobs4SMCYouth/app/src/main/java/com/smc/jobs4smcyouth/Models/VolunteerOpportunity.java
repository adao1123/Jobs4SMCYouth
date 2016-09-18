package com.smc.jobs4smcyouth.Models;

/**
 * Created by adao1 on 9/17/2016.
 */
public class VolunteerOpportunity {
    private String title;
    private String organizer;
    private String address;
    private String date;
    private String about;
    private String website;

    public VolunteerOpportunity() {
    }

    public VolunteerOpportunity(String title, String organizer, String address, String date, String about, String website) {
        this.title = title;
        this.organizer = organizer;
        this.address = address;
        this.date = date;
        this.about = about;
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
