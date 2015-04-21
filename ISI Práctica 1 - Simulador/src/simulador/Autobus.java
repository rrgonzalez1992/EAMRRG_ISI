package simulador;

import java.util.Random;

public class Autobus extends Thread{
	private String busId;
	private double latitud;
	private double longitud;
	private static final double bordeInferior = 3.3333333;
	private static final double bordeSuperior = 35.3333333;

	public Autobus(String id) {
		this.busId = id;
		latitud = longitud = (bordeInferior + bordeSuperior) / 2;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public String getBusId() {
		return busId;
	}

	public static double getBordeinferior() {
		return bordeInferior;
	}

	public static double getBordesuperior() {
		return bordeSuperior;
	}
	
	public void atasco() throws InterruptedException{
		System.out.println("El bus " + busId + " está en un atasco");
		this.sleep(60000);
	}

	public void run() {
		while(true){
			int desplazamiento = new Random().nextInt(4);
			int atascoRandom = new Random().nextInt(3);
			if(atascoRandom%3 == 1){
				try {
					atasco();
				} catch (InterruptedException e) {}
			} else {
				switch (desplazamiento) {
					case 0:
						longitud += 0.01;
						latitud += 0.02;
						break;
					case 1:
						longitud += 0.02;
						latitud -= 0.01;
						break;
					case 2:
						longitud += 0.03;
						latitud += 0.02;
						break;
					case 3:
						longitud -= 0.02;
						latitud += 0.04;
						break;
				}
			}
		}
	}

	public String toString() {
		return "Bus " + busId + "\nLatitud: " + latitud + "\nLongitud: "
				+ longitud + ".";

	}
}

