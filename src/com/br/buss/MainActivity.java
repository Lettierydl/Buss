package com.br.buss;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		
		SupportMapFragment fragment = 
				(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);  

		GoogleMap map = fragment.getMap();
		
		
		
		
		LatLng latLng = new LatLng(-7.098023,-34.840763); 
		MarkerOptions ponto = criarMarketOptions(latLng, "Foccus", "Teste");
	    map.addMarker(ponto);  
	    ponto.draggable(true);
	   
	    map.setMyLocationEnabled(true);
	    
	    map.setTrafficEnabled(true);

	    addCliculo(map , latLng, 100);
	    
	    List<LatLng> pontos = new ArrayList<LatLng>();
	    
	    pontos.add(new LatLng(-7.098024,-34.840769) );
	    pontos.add(new LatLng(-7.098124,-34.840863) );
	    
	    pontos.add(new LatLng(-7.098824,-34.840869) );
	    pontos.add(new LatLng(-7.098724,-34.840963) );
	    
	    criarPolilinhas(map,pontos);
	    
	    ativarBotaoDeLocalizacao(map);
	    
	    configuraPosicao3D(map, latLng);
	}

	//Ponto no mapa
	private MarkerOptions criarMarketOptions(LatLng latLng, String titulo, String subTitulo ){
		MarkerOptions pontoVisivel = new MarkerOptions();
		pontoVisivel.position(latLng);
		//pontoVisivel.icon(BitmapDescriptorFactory.fromResource(R.drawable.buss_frente)) ;
	    pontoVisivel.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
		pontoVisivel.title(titulo);
	    pontoVisivel.snippet(subTitulo);
		return pontoVisivel;
	}

	private void configuraPosicao(  
		    GoogleMap map, LatLng latLng) {  

		    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);  
		    map.animateCamera(  
		     CameraUpdateFactory.newLatLngZoom(latLng, 0.0f));  
    } 
	private void configuraPosicao3D(  
			  GoogleMap map, LatLng latLng) {  
			  map.moveCamera(  
			   CameraUpdateFactory.newLatLngZoom(latLng, 15));  
			  map.animateCamera(  
			   CameraUpdateFactory.zoomTo(10), 2000, null);  
			  
			  CameraPosition cameraPosition =   
			    new CameraPosition.Builder()  
			      .target(latLng)     
			      .zoom(17)       
			      .bearing(90)  
			      .tilt(45)  
			      .build();  
			  map.animateCamera(  
			    CameraUpdateFactory.newCameraPosition(  
			      cameraPosition));  
			}  
	
	private Circle addCliculo(GoogleMap map, LatLng centro, int raios){
		CircleOptions circleOptions = new CircleOptions()
	    .center(centro)
	    .radius(raios); // In meters

	// Get back the mutable Circle
		Circle circle = map.addCircle(circleOptions);
		return circle;
	}
	
	private void ativarBotaoDeLocalizacao(GoogleMap map){
		map.getUiSettings().setMyLocationButtonEnabled(true);
	}


	private void criarPolilinhas(GoogleMap map, List<LatLng> pontos){
		// Instantiates a new Polyline object and adds points to define a rectangle
		PolylineOptions rectOptions = new PolylineOptions();
		
		for(LatLng ponto : pontos){
			rectOptions.add(ponto);
		}
		// Get back the mutable Polyline
		Polyline polyline = map.addPolyline(rectOptions);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}