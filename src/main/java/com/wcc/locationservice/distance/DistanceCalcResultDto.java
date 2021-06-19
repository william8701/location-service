package com.wcc.locationservice.distance;

import com.wcc.locationservice.postcode.Postcode;

import java.util.ArrayList;
import java.util.List;

public class DistanceCalcResultDto {

    private List<Postcode> locations;

    private double distance;

    private String distanceStr;

    public DistanceCalcResultDto() {
        this.locations = new ArrayList<>();
    }

    public DistanceCalcResultDto(List<Postcode> locations, double distance, String distanceStr) {
        this.locations = locations;
        this.distance = distance;
        this.distanceStr = distanceStr;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDistanceStr() {
        return distanceStr;
    }

    public void setDistanceStr(String distanceStr) {
        this.distanceStr = distanceStr;
    }

    public void addLocation(Postcode postcode) {
        this.locations.add(postcode);
    }

    public List<Postcode> getLocations() {
        return locations;
    }

    public void setLocations(List<Postcode> locations) {
        this.locations = locations;
    }

}
