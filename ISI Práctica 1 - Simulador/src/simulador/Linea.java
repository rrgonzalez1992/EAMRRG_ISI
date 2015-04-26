package simulador;

import com.angryelectron.thingspeak.Channel;
import com.angryelectron.thingspeak.Entry;
import com.angryelectron.thingspeak.ThingSpeakException;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Linea extends Thread{
	private Autobus[] flota;
	private int numero;
	private String apiKey;
	private Channel canal;
	private Entry entrada;
	
	public Linea(int n, int buses, int canalNumero, String cadena){
		numero = n;
		flota = new Autobus[buses];
		apiKey = cadena;
		canal = new Channel(canalNumero,apiKey);
		entrada = new Entry();
	}
	
	public void inicializarFlota(){
		for(int i = 0; i < flota.length; i++){
			flota[i] = new Autobus("L" + numero + "B0" + (i+1), numero);
		}
		for(Autobus bus : flota){
			bus.start();
		}
	}
	
	public void run(){
		while(true){
			for(Autobus bus: flota){
				entrada.setField(1, bus.getBusId());
				entrada.setField(2, Double.toString(bus.getLatitud()));
				entrada.setField(3, Double.toString(bus.getLongitud()));
				try {
					canal.update(entrada);
				} catch (UnirestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ThingSpeakException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(bus.toString());
				try {
					Thread.sleep(16000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
