package com.br.buss;

import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.br.buss.geografic.aux.Auxiliares;
import com.br.buss.geografic.aux.Localizacao;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);

		GoogleMap map = fragment.getMap();
		
		if(map == null){
			Auxiliares.exibirMensagem("Mapa null", this);
		}else{
		
		Auxiliares.ativarLocalizacaoNoMapa(map);
		
		//Location local = map.getMyLocation();
		
		//LatLng latLng = new LatLng(local.getLatitude(),local.getLongitude());
		
		LatLng latLng = new LatLng(-7.097968,-34.840755);
		
//		Auxiliares.exibirMensagem("Informações da sua Localidade:\n" +
//				"Altitude: " + +local.getAltitude()+
//				"Precisão: " +local.getAccuracy()+
//				"Paciencia?: " +local.getBearing()+
//				"Provedor: " +local.getProvider()+
//				"Velocidade: " +local.getSpeed()+
//				"Tempo: " +local.getTime()
//				, this);
		
		
		
		Localizacao localizacao = new Localizacao(map, this){

			@Override
			public void getLocal(Location local) {
				Auxiliares.exibirMensagem("Informações da sua Localidade:\n" +
				"Altitude: " + +local.getAltitude()+
				"Precisão: " +local.getAccuracy()+
				"Paciencia?: " +local.getBearing()+
				"Provedor: " +local.getProvider()+
				"Velocidade: " +local.getSpeed()+
				"Tempo: " +local.getTime()
				, this.context);
				LatLng latLng = new LatLng(local.getLatitude(), local.getLongitude());
				map.addMarker(Auxiliares.criarPontoNoMapa(latLng, "Eu",
						"Minha Localização",
						BitmapDescriptorFactory.fromResource(R.drawable.buss_frente)));
				Auxiliares.configuraPosicao3D(map, latLng);
			}
			
			
			@Override
			public void activate(OnLocationChangedListener listener) {
				
				
			}

			@Override
			public void deactivate() {
				// TODO Auto-generated method stub
				
			}
			
		};
	
		
		Auxiliares.desenharTrafegoNoMapa(map);

		//map.addCircle(Auxiliares.criarCliculo(latLng, 200));

		Auxiliares.ativarBotaoDeLocalizacao(map);

		
		Address origem = null, destino = null;
//		try {
//			origem = Auxiliares.buscarCoordenadasDoEndereco(
//					"Rua Visconde de Itaparica - João Pessoa", this);
//			destino = Auxiliares.buscarCoordenadasDoEndereco(
//					"Rua Eugenio C Monteiro - João Pessoa", this);
//		} catch (IOException e) {
//			// nao existe rota
//			e.printStackTrace();
//		}

		//CriadoraDeRota.pintarRota(this, map, origem, destino);
		// criarRota(map, origem, destino);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}