package com.wcc.locationservice.postcode;

public class PostcodeDto {

    private Long id;

    private String postcode;

    private double latitude;

    private double longitude;

    public PostcodeDto(Long id, String postcode, double latitude, double longitude) {
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
