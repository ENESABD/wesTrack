package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.model.LatLng;

public class mapClickListener extends FragmentActivity implements GoogleMap.OnMapClickListener {
    @Override
    public void onMapClick(LatLng latLng) {
        System.out.println("hello");
        System.out.println(latLng.toString());

    }

}
