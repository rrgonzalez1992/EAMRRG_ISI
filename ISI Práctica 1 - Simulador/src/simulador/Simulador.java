package simulador;

import com.angryelectron.thingspeak.ThingSpeakException;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Simulador {
	public static void main(String[] args) throws UnirestException,
			ThingSpeakException, InterruptedException {
		Linea lineaUno = new Linea(1, 5, 34619, "353T4EJBX5C5SW3A");
		Linea lineaDos = new Linea(2, 5, 34636, "0JD5BNSNCIHACS7O");
		lineaUno.inicializarFlota();
		lineaDos.inicializarFlota();
		lineaUno.start();
		lineaDos.start();
	}
}
