package com.example.speedcalculation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Mapactivity extends MapActivity {
	
	public double MyCurLat = 0;
	public double MyCurLang = 0;
	
	public double LastclLat = 0;
	public double LastclLan = 0;
	
	String Type="";
	String InputLoc = "";
	
	
	MapView mapView;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.map);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
		
        LocationManager locationMangaer = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Mapactivity.MyLocationListener locationListener = new MyLocationListener();
		locationMangaer.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
		
        
        double lat = Double.parseDouble("48.85827758964043");
        double lon = Double.parseDouble("2.294543981552124");
        AddOverlayPoint(lat, lon);   
         
	}
	
	void AddOverlayPoint(double lat, double lon)
	{		
		LastclLat = lat; LastclLan = lon;
        MapController mc = mapView.getController();
        GeoPoint geoPoint = new GeoPoint((int)(lat * 1E6), (int)(lon * 1E6));
        mc.animateTo(geoPoint);
        mc.setZoom(15);
        mapView.invalidate(); 
        List<Overlay> mapOverlays = mapView.getOverlays();
        mapOverlays.clear();
        Drawable drawable = this.getResources().getDrawable(R.drawable.pointer);
        AddItemizedOverlay itemizedOverlay = new AddItemizedOverlay(drawable, this);
        OverlayItem overlayitem = new OverlayItem(geoPoint, "Hello", "Sample Overlay item");        
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);		
	}
	
	public void GetClicableLoc(double lat, double lon)
	{
		LastclLat = lat; LastclLan = lon;
	}


	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
    // Initiating Menu XML file (menu.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
		switch (item.getItemId())
        {            
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
        	MyCurLat = loc.getAltitude();
        	MyCurLang = loc.getLongitude();  
        	Mapactivity.this.AddOverlayPoint(loc.getAltitude(), loc.getLongitude());
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
}
