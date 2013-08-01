package com.br.buss.geografic;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Ponto {
	
	private String nome;
    private String descricao;
    private BitmapDescriptor icone = null;
    private double latitude;
    private double longitude;
   
    
    public Ponto(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Ponto(String nome, String descricao, BitmapDescriptor icone,
			double latitude, double longitude) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.icone = icone;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public void pintarNoMap(GoogleMap mapa){
    	LatLng latLng = new LatLng(latitude, longitude); 
		MarkerOptions ponto = criarMarketOptions(latLng, nome, descricao);
		mapa.addMarker(ponto);
    }
    
    public LatLng getLatLng(){
    	return  new LatLng(latitude, longitude);
    }
    
    private MarkerOptions criarMarketOptions(LatLng latLng, String titulo, String subTitulo ){
		MarkerOptions pontoVisivel = new MarkerOptions();
		pontoVisivel.position(latLng);
		if(icone == null){
			pontoVisivel.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
		}else{
			//pontoVisivel.icon(BitmapDescriptorFactory.fromResource(R.drawable.buss_frente));
		}
		pontoVisivel.title(titulo);
	    pontoVisivel.snippet(subTitulo);
	    pontoVisivel.draggable(true);
		return pontoVisivel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BitmapDescriptor getIcone() {
		return icone;
	}

	public void setIcone(BitmapDescriptor icone) {
		this.icone = icone;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
    
}
