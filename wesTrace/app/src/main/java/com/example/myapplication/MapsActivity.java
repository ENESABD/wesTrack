package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.LatLngBounds;
import com.google.android.libraries.maps.model.Marker;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String MAP_TYPE_KEY = "wes_Covid";
    private GoogleMap mMap;
    private int currentMapType = GoogleMap.MAP_TYPE_NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentMapType = savedInstanceState.getInt(MAP_TYPE_KEY);
        }
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
              .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(currentMapType);

        LatLng wesleyan = new LatLng(41.556418, -72.656432);
        mMap.addMarker(new MarkerOptions().position(wesleyan).title("Marker in Wesleyan").visible(false));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(wesleyan));

        mMap.setMinZoomPreference(17.0f);
        mMap.setMaxZoomPreference(19.0f);

        googleMap.setOnInfoWindowClickListener(this);

        LatLngBounds wesleyanBounds = new LatLngBounds(
                new LatLng(41.549570,-72.664640 ), // SW bounds
                new LatLng(41.562956, -72.650231)  // NE bounds
       );
        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(wesleyanBounds, 0));
        mMap.setLatLngBoundsForCameraTarget(wesleyanBounds);
        mMap.setBuildingsEnabled(true);
        Marker usdan = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.556683, -72.656840))
                .title("Usdan University Center")
                .snippet("TBD"));


        Marker fisk = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.557308, -72.654624))
                .title("Fisk Hall")
                .snippet("TBD"));

        //Marker admin = googleMap.addMarker(new MarkerOptions()
        //        .position(new LatLng(41.5561307,-72.656515))
        //        .title("Wesleyan University - Administrative Office")
        //        .snippet("TBD"));

        //Marker chapel = googleMap.addMarker(new MarkerOptions()
        //        .position(new LatLng(41.555636, -72.6566244))
          //      .title("Memorial Chapel")
         //       .snippet("TBD"));
        //Marker theater92 = googleMap.addMarker(new MarkerOptions()
        //        .position(new LatLng(41.5557188,-72.6567106))
        //        .title("92 Theater")
        //        .snippet("TBD"));

        Marker olin = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.554657,-72.657188))
                .title("Olin Memorial Library")
                .snippet("Click Here to Check In"));

        Marker exley = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.553831,-72.6569175))
                .title("Exley Science Center")
                .snippet("Click Here to Check In"));

        //Marker admission = googleMap.addMarker(new MarkerOptions()
                //.position(new LatLng(41.5568483,-72.6594477))
                //.title("Wesleyan Office of Admission")
                //.snippet("TBD"));

        Marker athletic = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.551284,-72.660936))
                .title("Freeman Athletic Center")
                .snippet("Click Here to Check In"));

        Marker beckham = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.556589,-72.657483))
                .title("Fayerweather - Beckham Hall")
                .snippet("Click Here to Check In"));

        Marker boger = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.556934,-72.656308))
                .title("Boger Hall")
                .snippet("Click Here to Check In"))
                ;

        Marker resourceCenter = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.554127,-72.653792))
                .title("Student Resource Center")
                .snippet("Click Here to Check In"));

        Marker religious = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.554021,-72.653948))
                .title("Office of Religious and Spiritual Life")
                .snippet("Click Here to Check In"));

        Marker allbritton = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.554235,-72.655915))
                .title("Allbritton Center")
                .snippet("Click Here to Check In"));

        Marker affairs = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.554690,-72.656576))
                .title("Public Affairs Center")
                .snippet("Click Here to Check In"));

        Marker shanklin = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.553741,-72.656387))
                .title("Shanklin Hall")
                .snippet("Click Here to Check In"));




        mapClickListener listener = new mapClickListener();
        mMap.setOnMapClickListener(listener);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(),
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, signinout.class);

        String message = marker.getTitle();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}