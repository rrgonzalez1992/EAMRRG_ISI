package simulador;

import java.util.Random;

public class Autobus {
	private int id;
	private double latitud;
	private double longitud;
	private static final double bordeInferior = 33.3333333;
	private static final double bordeSuperior = 35.3333333;

	public Autobus(int id) {
		this.id = id;
		latitud = longitud = (bordeInferior + bordeSuperior) / 2;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public int getId() {
		return id;
	}

	public static double getBordeinferior() {
		return bordeInferior;
	}

	public static double getBordesuperior() {
		return bordeSuperior;
	}

	public void simularLocalizacion() {
		int desplazamiento = new Random().nextInt(4);
		switch (desplazamiento) {
		case 0:
			latitud += 0.002;
			break;
		case 1:
			latitud -= 0.002;
			break;
		case 2:
			longitud += 0.002;
			break;
		case 3:
			longitud += 0.002;
			break;
		}
	}

	public String toString() {
		return "Bus #" + id + ". Latitud: " + latitud + ". Longitud: "
				+ longitud + ".";

	}
}
