package com.example.catherinaxu.mycityfinder;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by catherinaxu on 3/26/15.
 */
public class Loc {

    public String feature_name;
    public double lat;
    public double lng;
    public String description;

    public Loc (Address address, String description) {
        this.feature_name = address.getFeatureName();
        this.lat = address.getLatitude();
        this.lng = address.getLongitude();
        this.description = description;
    }
}

