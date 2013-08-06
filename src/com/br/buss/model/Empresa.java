package com.br.buss.model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class Empresa {

	private String nome;
	private String telefone;
	private String enredecoRua;
	private LatLng enredecoLatLng;


	public Empresa(String nome, String telefone, String enredecoRua,
			LatLng enredecoLatLng) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.enredecoRua = enredecoRua;
		this.enredecoLatLng = enredecoLatLng;
	}

	public Empresa(String nome, String telefone, String enredecoRua) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.enredecoRua = enredecoRua;
	}
	
	public void marcarEnderecoNoMapa(GoogleMap map){
		
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEnredecoRua() {
		return enredecoRua;
	}

	public LatLng getEnredecoLatLng() {
		return enredecoLatLng;
	}

	

}
