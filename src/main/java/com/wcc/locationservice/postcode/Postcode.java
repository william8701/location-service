package com.wcc.locationservice.postcode;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity(name = "postcodelatlng")
public class Postcode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String postcode;

    @NonNull
    private double latitude;

    @NonNull
    private double longitude;

    public Postcode() {
    }

    public Postcode(Long id, String postcode, double latitude, double longitude) {
        this.id = id;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
