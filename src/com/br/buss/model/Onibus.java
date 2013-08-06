package com.br.buss.model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.PolylineOptions;


public class Onibus {

	private String numero;
	private String nome;
	private Empresa empresa;
	private Rota rota;
	
	public Onibus(String numero, String nome, Empresa empresa) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.empresa = empresa;
	}
	
	public Onibus(String numero, String nome,
			Empresa empresa, Rota rota) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.empresa = empresa;
		this.rota = rota;
	}
	
	
	public void desenharRota(GoogleMap mapa){
		PolylineOptions desenhoDaRota = rota.getDesenhoDaRota();
		if(desenhoDaRota != null){
			mapa.addPolyline(desenhoDaRota);
		}
	}
	
	
	public Rota getRota() {
		return rota;
	}
	public void setRota(Rota rota) {
		this.rota = rota;
	}
	public String getNumero() {
		return numero;
	}
	public String getNome() {
		return nome;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	
	
	
	
}
