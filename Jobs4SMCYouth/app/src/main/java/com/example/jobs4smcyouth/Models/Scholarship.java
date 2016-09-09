package com.example.jobs4smcyouth.Models;

/**
 * Created by adao1 on 9/6/2016.
 */
public class Scholarship {
    private String title;
    private String contact;
    private String address;
    private String email;
    private String applicationDeadline;
    private String numberOfAwards;
    private String max;
    private String description;

    public Scholarship() {
    }

    public Scholarship(String title, String contact, String address, String email, String applicationDeadline, String numberOfAwards, String max, String description) {
        this.title = title;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.applicationDeadline = applicationDeadline;
        this.numberOfAwards = numberOfAwards;
        this.max = max;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(String applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public String getNumberOfAwards() {
        return numberOfAwards;
    }

    public void setNumberOfAwards(String numberOfAwards) {
        this.numberOfAwards = numberOfAwards;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
