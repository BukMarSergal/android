package com.example.bigprace;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private Random random = new Random();
    private int waypointCount = 0;
    private LatLng lastWaypointLatLng;
    private double fixedBearing = random.nextDouble() * 360; // Random initial bearing

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private static final int REQUEST_CODE_MAIN_ACTIVITY_4 = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);

        // Initialize MapView from layout
        mapView = findViewById(R.id.mapView2);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted, request location updates
            LocationRequest locationRequest = LocationRequest.create()
                    .setInterval(2000) // Update interval in milliseconds (2 seconds)
                    .setFastestInterval(2000)
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        // Update map with user's current location
                        LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.clear(); // Clear previous markers
                        mMap.addMarker(new MarkerOptions().position(userLatLng).title("Your Location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 17f)); // Zoom to user's location

                        // Add a new waypoint marker every 1.5 seconds relative to the last waypoint
                        addRandomWaypoint();
                    }
                }
            };

            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

        } else {
            // Request location permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void addRandomWaypoint() {
        if (lastWaypointLatLng == null) {
            // If it's the first waypoint, start near the user's location
            lastWaypointLatLng = mMap.getCameraPosition().target;
        } else {
            // Generate random distance between 10 and 20 meters
            double distance = 20 + random.nextDouble() * 30; // Random distance between 10 and 20 meters

            // Calculate new waypoint coordinates relative to the last waypoint using fixed bearing
            double lat1 = Math.toRadians(lastWaypointLatLng.latitude);
            double lon1 = Math.toRadians(lastWaypointLatLng.longitude);

            double lat2 = Math.asin(Math.sin(lat1) * Math.cos(distance / 6371000) +
                    Math.cos(lat1) * Math.sin(distance / 6371000) * Math.cos(Math.toRadians(fixedBearing)));
            double lon2 = lon1 + Math.atan2(Math.sin(Math.toRadians(fixedBearing)) * Math.sin(distance / 6371000) * Math.cos(lat1),
                    Math.cos(distance / 6371000) - Math.sin(lat1) * Math.sin(lat2));

            // Convert back to degrees
            lat2 = Math.toDegrees(lat2);
            lon2 = Math.toDegrees(lon2);

            LatLng waypointLatLng = new LatLng(lat2, lon2);
            waypointCount++;

            // Add the new waypoint marker to the map
            mMap.addMarker(new MarkerOptions().position(waypointLatLng).title("Waypoint " + waypointCount));

            // Update lastWaypointLatLng to the current waypoint for the next iteration
            lastWaypointLatLng = waypointLatLng;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start location updates
                startLocationUpdates();
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (fusedLocationClient != null && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    public void svapovac(View view) {
        // Handle button click to switch to MainActivity
        Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
        startActivityForResult(intent, REQUEST_CODE_MAIN_ACTIVITY_4);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MAIN_ACTIVITY_4) {
            // Handle any result if needed
        }
    }

}
