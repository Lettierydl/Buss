package com.br.buss.geografic.aux;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class Auxiliares {
	
	
	//Ponto no mapa
		public static MarkerOptions criarPontoNoMapa(LatLng latLng, String titulo, String subTitulo,
				BitmapDescriptor icon){
			MarkerOptions pontoVisivel = new MarkerOptions();
			pontoVisivel.position(latLng);
			pontoVisivel.icon(icon) ;
		    pontoVisivel.title(titulo);
		    pontoVisivel.snippet(subTitulo);
		    pontoVisivel.draggable(true);
			return pontoVisivel;
		}
		
		//Saber Cordenar de um determinado Endereco
		public static Address buscarCoordenadasDoEndereco(String endereco, Context context) throws IOException {  
		    Geocoder geoCoder = new Geocoder(context, Locale.getDefault());// esse Geocoder aqui é quem vai traduzir o endereço de String para coordenadas double  
		    List<Address> addresses = null;//este Adress aqui recebe um retorno do metodo geoCoder.getFromLocationName vc manipula este retorno pra pega as coordenadas  
		    addresses = geoCoder.getFromLocationName(endereco, 1);
		    return addresses.get(0);         
		}
	
		
		
		public static void configuraPosicao(  
			    GoogleMap map, LatLng latLng) {
			    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN); // Mapa Padrao 
			    map.animateCamera(  
			     CameraUpdateFactory.newLatLngZoom(latLng, 0.0f));  
	    } 
		
		public static void configuraPosicao3D(  
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
		
		public static CircleOptions criarCliculo(LatLng centro, int raios){
			CircleOptions circleOptions = new CircleOptions()
		    .center(centro)
		    .radius(raios); // In meters
			return circleOptions;
		}
		
		
		public static void ativarBotaoDeLocalizacao(GoogleMap map){
			map.getUiSettings().setMyLocationButtonEnabled(true);
		}

		public static void desenharTrafegoNoMapa(GoogleMap map){
			map.setTrafficEnabled(true);map.setMyLocationEnabled(true);
		}
		
		public static void ativarLocalizacaoNoMapa(GoogleMap map){
			map.setMyLocationEnabled(true);
		}

		public static PolylineOptions criarPolilinhas(List<LatLng> pontos){
			// Instantiates a new Polyline object and adds points to define a rectangle
			PolylineOptions rectOptions = new PolylineOptions();
			for(LatLng ponto : pontos){
				rectOptions.add(ponto);
			}
			return rectOptions;
		}
		
		
		public static void exibirMensagem(String mesagem , Context contexto){
			 Toast.makeText(contexto, mesagem, Toast.LENGTH_SHORT).show();
		}
	
}
