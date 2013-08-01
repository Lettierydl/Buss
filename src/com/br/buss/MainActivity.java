package com.br.buss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.br.buss.geografic.Route;
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
import com.google.android.maps.Overlay;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		SupportMapFragment fragment = 
				(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);  

		GoogleMap map = fragment.getMap();
		
		LatLng latLng = new LatLng(-7.098023, -34.840763); 
		MarkerOptions ponto = criarMarketOptions(latLng, "Foccus", "Teste");
		map.addMarker(ponto);  
	   
		
	    map.setMyLocationEnabled(true);
	    
	    map.setTrafficEnabled(true);

	    addCliculo(map , latLng, 100);
	    
	    ativarBotaoDeLocalizacao(map);
	    
	    configuraPosicao3D(map, latLng);
	    
	    Address origem = buscarCoordenadasEndereco("Rua Visconde de Itaparica, 76 - João Pessoa");
	    Address destino = buscarCoordenadasEndereco("Rua Eugenio C Monteiro, 51 - João Pessoa");

	    Route.criarRota(this, map, origem, destino);
	    //criarRota(map, origem, destino);
	    
	}

	//Ponto no mapa
	private MarkerOptions criarMarketOptions(LatLng latLng, String titulo, String subTitulo ){
		MarkerOptions pontoVisivel = new MarkerOptions();
		pontoVisivel.position(latLng);
		pontoVisivel.icon(BitmapDescriptorFactory.fromResource(R.drawable.buss_frente)) ;
	   // pontoVisivel.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
		pontoVisivel.title(titulo);
	    pontoVisivel.snippet(subTitulo);
	    pontoVisivel.draggable(true);
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
			   CameraUpdateFactory.zoomTo(2), 2000, null);  
			  
			  CameraPosition cameraPosition =   
			    new CameraPosition.Builder()  
			      .target(latLng)     
			      .zoom(17)       
			      .tilt(45)  
			      .build();  //.bearing(90) 
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
	
	public Address buscarCoordenadasEndereco(String endereco) {  
	    Geocoder geoCoder = new Geocoder(this, Locale.getDefault());// esse Geocoder aqui é quem vai traduzir o endereço de String para coordenadas double  
	    List<Address> addresses = null;//este Adress aqui recebe um retorno do metodo geoCoder.getFromLocationName vc manipula este retorno pra pega as coordenadas  
	  
	    try {
			addresses = geoCoder.getFromLocationName(endereco, 1);
	    return addresses.get(0);
	    } catch (IOException e) {
			e.printStackTrace();
		}         
	    return null;
	} 
	
	public void exibirMensagem(String mesagem){
		 Toast.makeText(getApplicationContext(), mesagem, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private InputStream getConnection(String url) {
        InputStream is = null;
        try {
                URLConnection conn = new URL(url).openConnection();
                is = conn.getInputStream();
        } catch (MalformedURLException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return is;
}


}