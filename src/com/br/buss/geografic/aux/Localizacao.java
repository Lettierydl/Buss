package com.br.buss.geografic.aux;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;

public abstract class Localizacao implements LocationSource, LocationListener{
	
	protected GoogleMap map;
	//private ProgressDialog dialog;
	protected Context context;
	public Location local;
	LocationManager locationManager;
	
	public Localizacao(GoogleMap map ,Context context){
		this.context = context;
		this.map = map;
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}
	
	@Override
    public void onLocationChanged(Location location) {
        /* ..and Animate camera to center on that location !
         * (the reason for we created this custom Location Source !) */
        map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
    }
	
	
	public abstract void getLocal(Location local);

}
