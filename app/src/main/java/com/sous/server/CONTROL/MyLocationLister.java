package com.sous.server.CONTROL;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/*---------- Listener class to get coordinates ------------- */
class MyLocationListener implements LocationListener {
    private  Context context;
    private String TAG;

    public MyLocationListener(Context context) {
        this.context = context;
        TAG = getClass().getName().toString();
        Log.i(TAG, "MyLocationListener GPS "+context);
    }

    @Override
    public void onLocationChanged(Location loc) {
        String longitude = "Longitude: " + loc.getLongitude();
        /*------- To get city name from coordinates -------- */
        Log.i(TAG, "MyLocationListener GPS longitude "+longitude);
      /*  String cityName = null;
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        Log.i(TAG, "MyLocationListener GPS gcd "+gcd);
        List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                Log.i(TAG, "MyLocationListener GPS addresses "+addresses);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
                Log.i(TAG, "MyLocationListener GPS cityName "+cityName);
            }
        Log.i(TAG, "MyLocationListener GPS addresses "+addresses);*/
        }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
        Log.i(TAG, "MyLocationListener GPS requestCode "+requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
        Log.i(TAG, "MyLocationListener GPS extras "+extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
        Log.i(TAG, "MyLocationListener GPS provider "+provider);
    }
}