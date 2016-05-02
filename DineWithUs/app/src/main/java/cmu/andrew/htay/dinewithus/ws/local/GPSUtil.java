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
public class GPSUtil {

    public double latitude;
    public double longitude;
    public boolean hasLocation;
    private LocationManager locMgr;
    private Activity parentActivity;

    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;



    public GPSUtil(LocationManager locMgr, Activity parentActivity)  {
        this.latitude = -1;
        this.longitude = -1;
        this.hasLocation = false;

        //store loc manager
        this.locMgr = locMgr;
        this.parentActivity = parentActivity;

        ActivityCompat.requestPermissions(parentActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_ACCESS_FINE_LOCATION);
        if (ActivityCompat.checkSelfPermission(parentActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(parentActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationListener locListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    hasLocation = true;
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
        Location loc = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(loc != null) {
            this.latitude = loc.getLatitude();
            this.longitude = loc.getLongitude();
        }
        loc = locMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(loc != null) {
            this.latitude = loc.getLatitude();
            this.longitude = loc.getLongitude();
        }
        this.locMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
        this.locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
        //attach listener to update location changes

    }

    public void syncGPS() {

    }

    public void onStatusChanged(){

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() { return longitude; }



}
