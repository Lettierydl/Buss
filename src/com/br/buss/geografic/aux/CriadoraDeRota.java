package com.br.buss.geografic.aux;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.location.Address;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class CriadoraDeRota {
	private final LinkedList<LatLng> points;
	private String polyline;

	public CriadoraDeRota() {
		points = new LinkedList<LatLng>();
	}

	public void addPoints(final List<LatLng> points) {
		this.points.addAll(points);
	}

	public List<LatLng> getPoints() {
		return points;
	}

	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}

	public String getPolyline() {
		return polyline;
	}
	
	public static void criarRota(Context ctx, GoogleMap map , LatLng origem, LatLng destino){
		new RotaAsyncTask(ctx, map).execute(  
			      // Latitude, Logintude de Origem  
			      origem.latitude, origem.longitude,      
			      // Latitude, Longitude de Destino  
			      destino.latitude, destino.longitude);    
			     
	}

	public static void pintarRota(Context ctx, GoogleMap map,
			Address origem, Address destino) {
		new RotaAsyncTask(ctx, map).execute(  
			      // Latitude, Logintude de Origem  
			      origem.getLatitude(), origem.getLongitude(),      
			      // Latitude, Longitude de Destino  
			      destino.getLatitude(), destino.getLongitude());    
		
	}
	

	
}