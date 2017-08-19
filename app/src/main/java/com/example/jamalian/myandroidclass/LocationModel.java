package com.example.jamalian.myandroidclass;

import com.orm.SugarRecord;

/**
 * Created by Jamalian on 8/19/2017.
 */

public class LocationModel extends SugarRecord<LocationModel> {
    Long id;
    String name;
    double latitude;
    double longtitude;

    public LocationModel(int i, String home, double latitude, double longtitude) {

    }

    public LocationModel() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public LocationModel(Long id, String name, double latitude, double longtitude) {

        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
