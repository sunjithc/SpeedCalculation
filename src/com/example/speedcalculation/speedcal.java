package com.example.speedcalculation;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class speedcal extends Activity {
	

Timer timer = new Timer();

Location ExLoc = null, CurLoc = null;

float Dist = 0;


@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.speedcalc);

timer = new Timer();
timer.schedule(new GetLoc(),0, 1000);

LocationManager locationMangaer = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
MyLocationListener locationListener = new MyLocationListener();
locationMangaer.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);

}


@Override
protected void onDestroy() {
	Toast.makeText(speedcal.this, "Timer Stopped", Toast.LENGTH_SHORT).show();
    timer.cancel();    
    finish();
	
	super.onDestroy();
}


private class MyLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location loc) {
    	CurLoc = loc;    	        	
		    }
//
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub        	
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub        	
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub        	
    }
}

class GetLoc extends TimerTask {

    @Override
    public void run() {
        speedcal.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
            	try{
            		if(CurLoc!=null && ExLoc != null)
            		{
            	Dist =	 CurLoc.distanceTo(ExLoc);
            	ExLoc = CurLoc;
            		}
            	}catch (Exception e) {
					// TODO: handle exception
				}
            	((TextView) speedcal.this.findViewById(R.id.TVSPEED)).setText("Distance is :"+String.valueOf(Dist)+"per Minute");
            	
            }
        });
    }
};

}
