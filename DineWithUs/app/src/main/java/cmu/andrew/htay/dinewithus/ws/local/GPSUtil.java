package cmu.andrew.htay.dinewithus.ws.local;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HuiJun on 4/14/16.
 */
public class GPSUtil  {

    private double latitude;
    private double longitude;
    private LocationManager locMgr;
    private Activity parentActivity;

    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;

    public GPSUtil(LocationManager locMgr, Activity parentActivity) {
        this.latitude = -1;
        this.longitude = -1;

        //store loc manager
        this.locMgr = locMgr;
        this.parentActivity = parentActivity;

        //attach listener to update location changes
        LocationListener locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    public void syncGPS() {


    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;

    }


}
