package com.br.buss.model;

import java.util.LinkedList;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class Rota {

	private LinkedList<LatLng> pontos = new LinkedList<LatLng>();
	private PolylineOptions desenhoDaRota = new PolylineOptions();
	private LinkedList<String> ruas = new LinkedList<String>();
	
	
	public Rota(LinkedList<String> ruas) {
		super();
		this.ruas = ruas;
	}
	
	
	public Rota(LinkedList<LatLng> pontos, LinkedList<String> ruas) {
		super();
		this.pontos = pontos;
		this.ruas = ruas;
	}

	
	public Rota(LinkedList<LatLng> pontos, PolylineOptions desenhoDaRota,
			LinkedList<String> ruas) {
		super();
		this.pontos = pontos;
		this.desenhoDaRota = desenhoDaRota;
		this.ruas = ruas;
	}


	public PolylineOptions getDesenhoDaRota() {
		return desenhoDaRota;
	}


	public LinkedList<LatLng> getPontos() {
		return pontos;
	}


	public LinkedList<String> getRuas() {
		return ruas;
	}
	
	
		
}
