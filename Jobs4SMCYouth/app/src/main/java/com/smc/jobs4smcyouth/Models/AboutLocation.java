package com.smc.jobs4smcyouth.Models;

/**
 * Created by samsiu on 9/6/16.
 */
public class AboutLocation {

    private Double latitude;
    private Double longitude;
    private String name;
    private String address;
    private String notes;
    private String faxNumber;
    private String phoneNumber;

    public AboutLocation(Double latitude, Double longitude, String name, String address, String notes, String faxNumber, String phoneNumber) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.address = address;
        this.notes = notes;
        this.faxNumber = faxNumber;
        this.phoneNumber = phoneNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNotes() {
        return notes;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
